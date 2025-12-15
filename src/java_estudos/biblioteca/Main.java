package java_estudos.biblioteca;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Biblioteca biblioteca = new Biblioteca();

        System.out.println("Bem-vindo(a) ao Sistema de Biblioteca!");

        boolean rodando = true;
        while (rodando) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Cadastrar Usuário");
            System.out.println("3. Realizar Empréstimo");
            System.out.println("4. Buscar Livro pelo Título");
            System.out.println("5. Buscar Usuário pelo Nome");
            System.out.println("6. Devolver Livro");
            System.out.println("7. Sair");
            System.out.println("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n--- Novo Livro ---");
                    System.out.println("Digite o título: ");
                    String titulo = scanner.nextLine();
                    System.out.println("Digite o autor: ");
                    String autor = scanner.nextLine();

                    Livro novoLivro = new Livro(titulo, autor);
                    biblioteca.cadastrarLivro(novoLivro);

                    System.out.println("Livro cadastrado!");
                    System.out.println("ID do Livro (Copie para usar depois): " + novoLivro.getLivroID());
                    break;

                case 2:
                    System.out.println("\n--- Novo Usuário ---");
                    System.out.println("Digite o nome: ");
                    String nome = scanner.nextLine();

                    Usuario novoUsuario = new Usuario(nome);
                    biblioteca.cadastrarUsuario(novoUsuario);

                    System.out.println("Usuário Cadastrado!");
                    System.out.println("ID do Usuário (copie para usar depois): " + novoUsuario.getUsuarioID());
                    break;

                case 3:
                    System.out.println("\n--- Empréstimo ---");

                    String idLivroFinal = selecionarLivroPorTitulo(scanner, biblioteca);
                    if (idLivroFinal == null) break;

                    String idUsuarioFinal = selecionarUsuarioPorNome(scanner, biblioteca);
                    if (idUsuarioFinal == null) break;

                    biblioteca.realizarEmprestimo(idLivroFinal, idUsuarioFinal);
                    break;

                case 4:
                    System.out.println("Digite o título do livro: ");
                    String termoLivro = scanner.nextLine();
                    biblioteca.buscarLivroPorTitulo(termoLivro);
                    break;

                case 5:
                    System.out.println("Digite o nome do usuário: ");
                    String termoUsuario = scanner.nextLine();
                    biblioteca.buscarUsuarioPorNome(termoUsuario);
                    break;

                case 6:
                    System.out.println("\n--- Devolução ---");

                    List<Emprestimo> pendencias = biblioteca.getEmprestimosAtivos();

                    if (pendencias.isEmpty()) {
                        System.out.println("Nenhum livro emprestado no momento.");
                    } else {
                        System.out.println("Selecione qual livro está sendo devolvido: ");

                        for (int i = 0; i < pendencias.size(); i++) {
                            Emprestimo e = pendencias.get(i);
                            System.out.println((i + 1) + ". Livro: " + e.getLivro().getTitulo() +
                                    " | Usuário: " + e.getUsuario().getNome() +
                                    " | Devolução: " + e.getDataDevolucao() +
                                    " | Multa: " + (e.estaAtrasado() ? "R$" + e.calcularMulta() : "Não está atrasado"));
                        }
                        System.out.println("Digite o número da opção desejada: ");
                        String entradaDevolucao = scanner.nextLine();

                        try {
                            int escolha = Integer.parseInt(entradaDevolucao);

                            if (escolha > 0 && escolha <= pendencias.size()) {
                                Emprestimo emprestimoSelecionado = pendencias.get(escolha - 1);
                                String idParaDevolver = emprestimoSelecionado.getLivro().getLivroID();
                                biblioteca.realizarDevolucao(idParaDevolver);
                            } else {
                                System.out.println("Opção inválida.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Erro: Digite apenas NÚMEROS.");
                        }
                    }
                    break;

                case 7:
                    System.out.println("Encerrando o sistema...");
                    rodando = false;
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }

    private static String selecionarLivroPorTitulo(Scanner scanner, Biblioteca biblioteca) {
        System.out.println("Digite o título do livro: ");
        String termo = scanner.nextLine();

        List<Livro> encontrados = biblioteca.pesquisarLivros(termo);

        if (encontrados.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
            return null;
        }

        if (encontrados.size() == 1) {
            Livro l = encontrados.get(0);
            System.out.println("Livro selecionado: " + l.getTitulo());
            return l.getLivroID();
        }

        System.out.println("Mais de um livro encontrado. Informe qual é o correto.");
        for (int i = 0; i < encontrados.size(); i++) {
            System.out.println((i + 1) + ". " + encontrados.get(i).getTitulo() + " (" + encontrados.get(i).getAutor() + ")");
        }
        System.out.println("Digite o número da opção desejada: ");
        String entrada = scanner.nextLine();

        try {
            int escolha = Integer.parseInt(entrada);

            if (escolha > 0 && escolha <= encontrados.size()) {
                Livro livroSelecionado = encontrados.get(escolha - 1);
                System.out.println("Opção selecionada: " + livroSelecionado.getTitulo());
                return livroSelecionado.getLivroID();
            } else {
                System.out.println("Opção inválida!");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Você deve digitar um NÚMERO.");
            return null;
        }
    }

    private static String selecionarUsuarioPorNome(Scanner scanner, Biblioteca biblioteca) {
        System.out.println("Digite o nome do usuário: ");
        String termo = scanner.nextLine();
        List<Usuario> encontrados = biblioteca.pesquisarUsuarios(termo);

        if (encontrados.isEmpty()) {
            System.out.println("Usuário não encontrado.");
            return null;
        }

        if (encontrados.size() == 1) {
            Usuario u = encontrados.get(0);
            System.out.println("Usuário selecionado: " + u.getNome());
            return u.getUsuarioID();
        }

        System.out.println("Múltiplos usuários encontrados: ");
        for (int i = 0; i < encontrados.size(); i++) {
            System.out.println((i + 1) + ". " + encontrados.get(i).getNome());
        }
        System.out.println("Digite o número da opção desejada");
        String entrada = scanner.nextLine();

        try {
            int escolha = Integer.parseInt(entrada);

            if (escolha > 0 && escolha <= encontrados.size()) {
                Usuario usuarioSelecionado = encontrados.get(escolha - 1);
                System.out.println("Opção selecionada: " + usuarioSelecionado.getNome());
                return usuarioSelecionado.getUsuarioID();
            } else {
                System.out.println("Opção inválida");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite apenas NÚMEROS.");
            return null;
        }
    }
}
