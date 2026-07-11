// Classe abstrata que serve de base para todos os tipos de usuários (implementa o encapsulamento de dados cadastrais e validações preventivas)

public abstract class Usuario {

    private String nome;
    private String email;


    //Construtor
    public Usuario(String nome, String email) {
        this.nome = nome;
        if (email != null && email.contains("@")) { //Verifica erro no input do email
            this.email = email;
        } else {
            this.email = "Email invalido";
        }
    }

    // Métodos abstratos que forçam as subclasses a definirem suas próprias regras de negócio
    public abstract int getPrazoEmprestimo(); //Quantos dias o usúario tem para ficar com o livro
    public abstract double getTaxaMulta(); //Taxa de multa diária

    //Métodos de encapsulamento (getters e setters)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
            return true;
        }
        return false;
    }
}
