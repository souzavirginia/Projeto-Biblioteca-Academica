// Interface que define as operações obrigatórias para qualquer item que possa ser retirado do acervo da biblioteca

public interface Emprestavel {

    void emprestar(); // Altera o estado do item para indisponível
    void devolver(); // Altera o estado do item para disponível
    boolean estaDisponivel(); // Verifica a disponibilidade atual do item

}
