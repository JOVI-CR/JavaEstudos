package java_estudos.biblioteca;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {
    private List<Livro> catalogo;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimosAtivos;

    public Biblioteca() {
        this.catalogo = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimosAtivos = new ArrayList<>();
    }

    public void cadastrarLivro(Livro livro) {
        this.catalogo.add(livro);
        System.out.println("Livro cadastrado: " + livro.getTitulo());
    }

    public void cadastrarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        System.out.println("Usuário cadastrado: " + usuario.getNome());
    }

    public void realizarEmprestimo(String idLivro, String idUsuario) {
        Livro livroEncontrado = buscarLivroPorId(idLivro);
        Usuario usuarioEncontrado = buscarUsuarioPorId(idUsuario);

        if (livroEncontrado == null) {
            System.out.println("Erro: Livro não encontrado.");
            return;
        }
        if (usuarioEncontrado == null) {
            System.out.println("Erro: Usuário não encontrado.");
            return;
        }

        if (livroEncontrado.emprestar()) {
            Emprestimo novoEmprestimo = new Emprestimo(livroEncontrado, usuarioEncontrado);
            usuarioEncontrado.adicionarEmprestimo(novoEmprestimo);

            this.emprestimosAtivos.add(novoEmprestimo);
            System.out.println("Empréstimo realizado com sucesso!");
            System.out.println("Devolução prevista para " + novoEmprestimo.getDataDevolucao());
        } else {
            System.out.println("Erro: O livro já está emprestado para outra pessoa.");
        }
    }

    public void realizarDevolucao(String idLivro) {
        Livro livroEncontrado = buscarLivroPorId(idLivro);

        if (livroEncontrado == null) {
            System.out.println("Erro: Livro não encontrado.");
            return;
        }

        Emprestimo emprestimo = buscarEmprestimoPorLivro(livroEncontrado);
        if (emprestimo == null) {
            System.out.println("Erro: Não consta empréstimo ativo para este livro.");
            return;
        }

        if (emprestimo.estaAtrasado()) {
            System.out.println("Devolução com atraso!");
            System.out.println("Multa a pagar: R$" + emprestimo.calcularMulta());
            System.out.println("Receba o pagamento antes de confirmar a devolução.");

            Scanner scannerPagamento = new Scanner(System.in);


            while (true) {
                System.out.println("A multa foi paga? [S/N]: ");
                String resposta = scannerPagamento.nextLine();

                if (resposta.equalsIgnoreCase("N")) {
                    System.out.println("Processo cancelado! O pagamento é obrigatório.");
                    return;
                }
                else if (resposta.equalsIgnoreCase("S")) {
                    System.out.println("Pagamento confirmado!");
                    break;
                } else {
                    System.out.println("Resposta inválida! Digite apenas 'S' ou 'N'.");
                }
            }
        }
        livroEncontrado.devolver();
        emprestimosAtivos.remove(emprestimo);
        System.out.println("Livro '" + livroEncontrado.getTitulo() + "' devolvido com sucesso!");
    }

    public void buscarLivroPorTitulo(String termo) {
        System.out.println("\nBuscando livros com: '" + termo + "'");
        boolean encontrou = false;

        for (Livro l : catalogo) {
            if (l.getTitulo().toLowerCase().contains(termo.toLowerCase())) {
                System.out.println("--------------------------------");
                System.out.println("Título: " + l.getTitulo());
                System.out.println("Autor: " + l.getAutor());
                System.out.println("ID: " + l.getLivroID());
                System.out.println("Disponibilidade: " + (l.isDisponivel() ? "Disponível" : "Indisponível"));
                if (!l.isDisponivel()) {
                    Emprestimo emp = buscarEmprestimoPorLivro(l);

                    if (emp != null) {
                        System.out.println("Data Retirada: " + emp.getDataRetirada());
                        System.out.println("Devolução Prevista: " + emp.getDataDevolucao());
                        System.out.println("Está com: " + emp.getUsuario().getNome());
                    }
                    if (emp.estaAtrasado()) {

                        long diasDeAtraso = ChronoUnit.DAYS.between(emp.getDataDevolucao(), LocalDate.now());
                        System.out.println("O livro está com atraso de " + diasDeAtraso + " dias!!!");
                        System.out.println("A multa atual é de: R$" + emp.calcularMulta());
                    }
                }
                System.out.println("--------------------------------");
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum livro encontrado.");
        }
    }

    public void buscarUsuarioPorNome(String termo) {
        System.out.println("\nBuscando usuários com: '" + termo + "'");
        boolean encontrou = false;

        for (Usuario u : usuarios) {
            if (u.getNome().toLowerCase().contains(termo.toLowerCase())) {
                System.out.println("--------------------------------");
                System.out.println("Nome: " + u.getNome());
                System.out.println("ID: " + u.getUsuarioID());
                System.out.println("--------------------------------");
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum usuário encontrado.");
        }
    }

    public List<Livro> pesquisarLivros(String termo) {
        List<Livro> resultados = new ArrayList<>();

        for (Livro l : catalogo) {
            if (l.getTitulo().toLowerCase().contains(termo.toLowerCase())) {
                resultados.add(l);
            }
        }
        return resultados;
    }

    public List<Usuario> pesquisarUsuarios (String termo) {
        List<Usuario> resultados = new ArrayList<>();

        for (Usuario u : usuarios) {
            if (u.getNome().toLowerCase().contains(termo.toLowerCase())) {
                resultados.add(u);
            }
        }
        return resultados;
    }

    public List<Emprestimo> getEmprestimosAtivos() {
        return this.emprestimosAtivos;
    }

    private Emprestimo buscarEmprestimoPorLivro(Livro livro) {
        for (Emprestimo e : emprestimosAtivos) {
            if (e.getLivro().getLivroID().equals(livro.getLivroID())) {
                return e;
            }
        }
        return null;
    }

    private Livro buscarLivroPorId(String id) {
        for (Livro l : catalogo) {
            if (l.getLivroID().equals(id)) {
                return l;
            }
        }
        return null;
    }

    private Usuario buscarUsuarioPorId(String id) {
        for (Usuario u : usuarios) {
            if (u.getUsuarioID().equals(id)) {
                return u;
            }
        }
        return null;
    }
}
