import java.util.List;
import java.util.Scanner;


public class Main {

    static Scanner input = new Scanner(System.in);
    static Biblioteca biblioteca = new Biblioteca();

    public static void main(String[] args) {

        // Roda simulações antes do menu do usuário
        System.out.println("Cadastro de Livros e Usuários");

        Usuario u1 = new Estudante("Katia Virginia", "katia123@gmail.com", "20252014040035");
        Usuario u2 = new ComunidadeExterna("Paulo Victor", "paulo123@gmail.com");

        biblioteca.cadastrarUsuario(u1);
        biblioteca.cadastrarUsuario(u2);

        Usuario usuarioBuscado = biblioteca.consultarUsuario("Katia Virginia");
        if (usuarioBuscado instanceof Estudante) {
            Estudante estudante = (Estudante) usuarioBuscado;
            System.out.println("Usuário(a): " + estudante.getNome() + " é um(a) estudante. Matrícula: " + estudante.getMatricula());
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

        System.out.println("\nIniciando Menu.\n");

        int opcao;

        do {
            exibirMenu();
            opcao = lerOpcaoInt();

            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> cadastrarUsuario();
                case 3 -> listarLivros();
                case 4 -> realizarEmprestimo();
                case 5 -> registrarDevolucao();
                case 6 -> exibirRelatorio();
                case 0 -> System.out.println("Adeus...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }

            System.out.println();

        } while (opcao != 0);

    }

    static void exibirMenu() {
        System.out.println("Biblioteca Acadêmica");
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Cadastrar Livro");
        System.out.println("2 - Cadastrar Usuário");
        System.out.println("3 - Listar Livros");
        System.out.println("4 - Realizar Empréstimo");
        System.out.println("5 - Registrar Devolução");
        System.out.println("6 - Relatório de Livros Mais Emprestados");
        System.out.println("0 - Sair");
    }

    //Cadastrar livro
    static void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = input.nextLine();
        System.out.print("Autor: ");
        String autor = input.nextLine();
        System.out.print("Editora: ");
        String editora = input.nextLine();
        System.out.print("Edição: ");
        int edicao = lerOpcaoInt();

        Livro livro = new Livro(titulo, autor, editora, edicao);
        biblioteca.cadastrarLivro(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    //Cadastrar Usuário
    static void cadastrarUsuario() {
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();

        System.out.println("Tipo de usuário:");
        System.out.println("1 - Estudante");
        System.out.println("2 - Comunidade Externa");
        int tipo = lerOpcaoInt();

        Usuario usuario;
        if (tipo == 1) {
            System.out.print("Matrícula: ");
            String matricula = input.nextLine();
            usuario = new Estudante(nome, email, matricula);
        } else {
            usuario = new ComunidadeExterna(nome, email);
        }

        biblioteca.cadastrarUsuario(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }


    //Listar os livros no acervo
    static void listarLivros() {
        List<Livro> livros = biblioteca.listarLivros();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }
        for (Livro l : livros) {
            String status = l.estaDisponivel() ? "Disponível" : "Emprestado";
            System.out.println("- " + l.getTitulo() + " (" + l.getAutor() + ") [" + status + "]");
        }
    }


    //Fazer empréstimo do livro
    static void realizarEmprestimo() {
        System.out.print("Título do livro: ");
        String titulo = input.nextLine();
        System.out.print("Nome do usuário: ");
        String nome = input.nextLine();
        System.out.print("Data do empréstimo (dd/mm/yyyy): ");
        String dataEmprestimo = input.nextLine();
        System.out.print("Data prevista de devolução (dd/mm/yyyy): ");
        String dataPrevista = input.nextLine();

        Emprestimo emp = biblioteca.realizarEmprestimo(titulo, nome, dataEmprestimo, dataPrevista);
        if (emp != null) {
            System.out.println("Empréstimo realizado com sucesso!");
        } else {
            System.out.println("Não foi possível realizar o empréstimo.");
        }
    }

    // Registrar a devolução do livro
    static void registrarDevolucao() {
        System.out.print("Título do livro: ");
        String titulo = input.nextLine();

        Emprestimo emp = biblioteca.buscarEmprestimoAtivo(titulo);
        if (emp == null) {
            System.out.println("Nenhum empréstimo ativo encontrado para esse livro.");
            return;
        }

        System.out.print("Data da devolução (dd/mm/yyyy): ");
        String dataDevolucao = input.nextLine();

        System.out.print("Dias de atraso: ");
        int diasAtraso = lerOpcaoInt();

        double multa = emp.registrarDevolucao(dataDevolucao, diasAtraso);
        System.out.printf("Devolução registrada. Multa: R$ %.2f%n", multa);
    }


    //Apresentar o relatório de empréstimos
    static void exibirRelatorio() {
        List<LivroMaisEmprestadoReport> relatorio = biblioteca.obterLivrosMaisEmprestados();
        if (relatorio.isEmpty()) {
            System.out.println("Nenhum dado disponível.");
            return;
        }
        for (LivroMaisEmprestadoReport r : relatorio) {
            System.out.println("- Livro: " + r.titulo() + " | Quantidade: " + r.quantidade());
        }
    }

    //Lê o número colocado pelo usuário, validando a entrada
    static int lerOpcaoInt() {
        while (!input.hasNextInt()) {
            System.out.print("Digite um número válido: ");
            input.next();
        }
        int valor = input.nextInt();
        input.nextLine();
        return valor;
    }
}


