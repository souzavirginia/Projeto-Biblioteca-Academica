# Sistema de Gerenciamento de Biblioteca Acadêmica

Este projeto consiste em uma aplicação, desenvolvida em **Java**, para gerenciamento de uma biblioteca acadêmica, que permite o empréstimo por alunos e pela comunidade externa, com regras diferenciadas. O sistema simula o fluxo operacional de controle de acervo, usuários e gestão de empréstimos, devoluções e multas.

---

## Pilares de POO Aplicados

*   **Encapsulamento:** A delimitação dos atributos é restrita (`private`) garantindo que a leitura e a modificação dos dados passem estritamente por validações nos métodos de acesso (`Getters`/`Setters`).
*   **Abstração e Herança:** A classe abstrata `Usuario` centraliza as características comuns, enquanto `Estudante` e `ComunidadeExterna` estendem essa base para herdar e especializar o comportamento..
*   **Polimorfismo:** Sobrescrita dinâmica (`@Override`) das regras de cobrança e prazos de devolução, aplicando regras mais brandas para alunos e restrições mais rígidas para o público externo.
*   **Interfaces:** Implementação do contrato comportamental `Emprestavel` para a indepêndencia das classes.
*   **Data Records:** Uso da funcionalidade  `record` para transporte imutável de dados operacionais.
*   **Coleções:**  Utilização de coleções (`ArrayList`/`HashMap`) para buscas rápidas indexadas por chaves e listar o histórico linear dos empréstimos.
*   **Exceções:** Bloqueio preventivo que impede empréstimos duplicados.
