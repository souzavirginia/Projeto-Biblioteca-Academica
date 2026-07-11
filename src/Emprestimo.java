// Gerencia a associação entre um Livro e um Usuário (responsável direta pela lógica de devolução e aplicação financeira de multas)

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private String dataEmprestimo;
    private String dataDevolucaoPrevista;
    private String dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario, String dataEmprestimo, String dataDevolucaoPrevista) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucao = null; // Inicializa sem data real até que a devolução ocorra
        livro.emprestar(); // Modifica o estado do livro de forma automática
    }

    //Registra o fim do empréstimo e retorna a multa gerada por atrasos
    public double registrarDevolucao(String dataDevolucao, int diasAtraso) {
        this.dataDevolucao = dataDevolucao;
        this.livro.devolver(); // Libera o livro de volta para a biblioteca

        if (diasAtraso > 0) {
            return diasAtraso * usuario.getTaxaMulta(); // Calcula a multa com base nas regras do tipo de usuário atual
        }
        return 0.0;
    }

    // Getters
    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public String getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }
}