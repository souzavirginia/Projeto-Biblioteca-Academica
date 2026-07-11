//Implementa a interface Emprestavel, com um contador de empréstimos

public class Livro implements Emprestavel {
    private String titulo;
    private String autor;
    private String editora;
    private int edicao;
    private boolean disponivel;
    private int totalEmprestimos; //Contador

    public Livro(String titulo, String autor, String editora, int edicao) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.edicao = edicao;
        this.disponivel = true; // Todo livro inicia disponível para empréstimo
        this.totalEmprestimos = 0;
    }

    @Override
    public void emprestar() {
        this.disponivel = false;
        this.totalEmprestimos += 1; // Incrementa o contador de quantas vezes o livro foi emprestado
    }

    //Métodos get e set
    @Override
    public void devolver() {
        this.disponivel = true;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


    public String getEditora() {
        return this.editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getEdicao() {
        return this.edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    @Override
    public boolean estaDisponivel() {
        return disponivel;
    }
    public int getTotalEmprestimos() {
        return this.totalEmprestimos;
    }
}
