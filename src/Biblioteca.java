// Classe de controle central e gerenciamento do livros e usuários

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Biblioteca {
    // Utilização de Mapas para buscas rápidas indexadas por strings chaves (Títulos/Nomes)
    private Map<String, Livro> livros = new HashMap<>();
    private Map<String, Usuario> usuarios = new HashMap<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    // CRUD Livros
    public void cadastrarLivro(Livro livro) {
        livros.put(livro.getTitulo(), livro);
    }

    public Livro consultarLivro(String titulo) {
        return livros.get(titulo);
    }

    public void deletarLivro(String titulo) {
        livros.remove(titulo);
    }

    public List<Livro> listarLivros() {
        return new ArrayList<>(livros.values());
    }

    // CRUD Usuários
    public void cadastrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getNome(), usuario);
    }

    public Usuario consultarUsuario(String nome) {
        return usuarios.get(nome);
    }

    public void deletarUsuario(String nome) {
        usuarios.remove(nome);
    }

    // Operações de Empréstimo

    // Executa a validação cruzada das entidades e cria a instância de Empréstimo se permitido
    public Emprestimo realizarEmprestimo (String tituloLivro, String nomeUsuario, String dataEmprestimo, String dataPrevista){
        Livro livro = livros.get(tituloLivro);
        Usuario usuario = usuarios.get(nomeUsuario);

        //Segurança contra falhas
        if (livro != null && usuario != null && livro.estaDisponivel()) {
            Emprestimo novoEmprestimo = new Emprestimo(livro, usuario, dataEmprestimo, dataPrevista);
            emprestimos.add(novoEmprestimo);
            return novoEmprestimo;
        }
        return null; // Retorna nulo se houver impedimento
    }

    //Busca por empréstimos pendentes de devolução de um determinado livro
    public Emprestimo buscarEmprestimoAtivo(String tituloLivro){
        for (Emprestimo e: emprestimos){
            if (e.getLivro().getTitulo().equals(tituloLivro) && e.getDataDevolucao() == null){
                return e;
            }
        }
        return null;
    }

    //Consolida e ordena o acervo com base na quantidade de emprestimos
    public List<LivroMaisEmprestadoReport> obterLivrosMaisEmprestados(){
        List<Livro> listaLivros = new ArrayList<> (livros.values()); //converte o mapa de livros em uma lista para poder ordenar

        //Ordena a lista de forma decrescente com base no total de empréstimos
        for (int i=0; i < listaLivros.size(); i++){
            for (int j=i+1; j < listaLivros.size(); j++){
                if(listaLivros.get(i).getTotalEmprestimos()<listaLivros.get(j).getTotalEmprestimos()){
                    Livro temp = listaLivros.get(i);
                    listaLivros.set(i,listaLivros.get(j));
                    listaLivros.set(j,temp);
                }
            }
        }

    //Incrementa a lista final mapeando os livros para o formato Record
    List <LivroMaisEmprestadoReport> relatorio = new ArrayList<>();
        for (Livro l: listaLivros){
            relatorio.add(new LivroMaisEmprestadoReport(l.getTitulo(), l.getTotalEmprestimos()));
        }
        return relatorio;
    }
}