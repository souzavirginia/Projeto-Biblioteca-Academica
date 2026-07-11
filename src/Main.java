// Classe principal que simula situações reais de empréstimo

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        System.out.println("Cadastro de Livros e Usuários");

        Usuario u1 = new Estudante("Katia Virginia", "katia123@gmail.com", "20252014040035");
        Usuario u2 = new ComunidadeExterna("Paulo Victor", "paulo123@gmail.com");

        biblioteca.cadastrarUsuario(u1);
        biblioteca.cadastrarUsuario(u2);

        Usuario usuarioBuscado = biblioteca.consultarUsuario("Katia Virginia");
        if (usuarioBuscado instanceof Estudante) {
            Estudante estudante = (Estudante) usuarioBuscado;
            System.out.println("O usuário cadastrado " + estudante.getNome() + " é um estudante. Matrícula: " + estudante.getMatricula());
        }

        //Cadastro de livros no acervo
        Livro l1 = new Livro("Entendendo Algoritimos", "Bhargava", "Novatec", 1);
        Livro l2 = new Livro("As Crônicas de Nárnia", "C. S. Lewis", "HarperCollins", 1);

        biblioteca.cadastrarLivro(l1);
        biblioteca.cadastrarLivro(l2);

        System.out.println("\nLivros e Usuários cadastrados com sucesso!\n");

        System.out.println("Empréstimos");

        //Situação 1: Sucesso no empréstimo
        biblioteca.realizarEmprestimo("Entendendo Algoritimos", "Katia Virginia", "11/07/2026", "21/07/2026");
        System.out.println("Livro 'Entendendo Algoritimos' emprestado para Katia.");

        //Situação 2: Falha no empréstimo
        Emprestimo empFalho = biblioteca.realizarEmprestimo("Entendendo Algoritimos", "Paulo Victor", "11/07/2026", "16/07/2026");
        if (empFalho == null) {
            System.out.println("Aviso: Paulo não pôde pegar 'Entendendo Algoritimos', livro já se encontra emprestado.");
        }

        System.out.println("\nDevolução com Atraso");
        Emprestimo empAtivo = biblioteca.buscarEmprestimoAtivo("Entendendo Algoritimos");
        if (empAtivo != null) {
            double valorMulta = empAtivo.registrarDevolucao("24/07/2026", 3); // Simulação de 3 dias de atraso na devolução
            System.out.printf("Livro devolvido por Katia. Multa calculada: R$ %.2f%n", valorMulta);
        }

        System.out.println("\nRelatório de Livros Mais Emprestados");
        // Exibição do ranking de livros mais procurados obtido pelo algoritmo de ordenação
        for (LivroMaisEmprestadoReport report : biblioteca.obterLivrosMaisEmprestados()) {
            System.out.println("- Livro: " + report.titulo() + " | Quantidade: " + report.quantidade());
        }
    }
}



