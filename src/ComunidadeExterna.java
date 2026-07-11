// * Subclasse que representa usuários sem vínculo direto com a instituição de ensino (aplica restrições mais severas de prazo e valores de multas para proteção do acervo)

public class ComunidadeExterna extends Usuario {

    public ComunidadeExterna(String nome, String email) {
        super(nome, email);
    }

    @Override
    public int getPrazoEmprestimo() {
        return 5; //A comunidade externa tem prazo menor
    }

    @Override
    public double getTaxaMulta() {
        return 3.00; // Igualmente, há taxa de multa diária maior
    }
}
