// Subclasse que representa um aluno da instituição (possui prazos maiores e multas menores, além de identificação por matrícula)

public class Estudante extends Usuario {

    private String matricula;

    public Estudante(String nome, String email, String matricula) {
        super(nome, email); //Chama a classe Usuario
        this.matricula = matricula;
    }

    @Override
    public int getPrazoEmprestimo() {
        return 10; //Alunos tem 10 dias de prazo
    }

    @Override
    public double getTaxaMulta() {
        return 1.50; // Multa diária de R$ 1,50 por atraso
    }

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
