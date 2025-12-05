package java_estudos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        boolean rodando = true;

        while (rodando) {
            System.out.println("=== AGENDA TELEFÔNICA ===");
            System.out.println("1. Adicionar Contato Pessoal");
            System.out.println("2. Adicionar Contato Comercial");
            System.out.println("3. Remover Contato");
            System.out.println("4. Listar Todos Contatos");
            System.out.println("5. Sair");
            System.out.println("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("--- Novo Contato Pessoal ---");
                    boolean dadosPessoaisValidos = false;

                    while (!dadosPessoaisValidos) {
                        try {
                            System.out.println("Digite o nome: ");
                            String nomeDigitado = scanner.nextLine();

                            System.out.println("Digite o telefone: ");
                            String telDigitado = scanner.nextLine();

                            System.out.println("Digite o apelido (Deixe em branco caso não deseje ter um): ");
                            String apelidoDigitado = scanner.nextLine();

                            ContatoPessoal novoContato = new ContatoPessoal(nomeDigitado, telDigitado, apelidoDigitado);
                            agenda.adicionarContato(novoContato);
                            dadosPessoaisValidos = true;
                            novoContato.exibirDetalhes();
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                            System.out.println("Tente novamente...\n");
                        }
                    }
                break;

                case 2:
                    System.out.println("--- Novo Contato Pessoal ---");
                    boolean dadosComerciaisValidos = false;

                    while (!dadosComerciaisValidos) {
                        try {
                            System.out.println("Digite o nome: ");
                            String nomeDigitado = scanner.nextLine();

                            System.out.println("Digite o nome da empresa: ");
                            String nomeEmpresaDigitado = scanner.nextLine();

                            System.out.println("Digite o telefone: ");
                            String telDigitado = scanner.nextLine();

                            ContatoComercial novoContatoComercial = new ContatoComercial(nomeDigitado, telDigitado, nomeEmpresaDigitado);
                            agenda.adicionarContato(novoContatoComercial);
                            dadosComerciaisValidos = true;
                            novoContatoComercial.exibirDetalhes();
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                            System.out.println("Tente novamente...\n");
                        }
                    }
                break;

                case 3:
                    System.out.println("Digite o nome do contato que deseja remover: ");
                    String nomeRemover = scanner.nextLine();
                    agenda.removerContato(nomeRemover);
                    break;

                case 4:
                    agenda.listarContatos();
                    break;

                case 5:
                    System.out.println("Saindo do programa...");
                    rodando = false;
                    break;
            }
        }

        scanner.close();
    }
}


