package java_estudos.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Usuario {
    private String usuarioID;
    private String nome;
    private List<Emprestimo> historicoEmprestimos;

    public Usuario(String nome) {
        this.usuarioID = UUID.randomUUID().toString();
        this.nome = nome;
        this.historicoEmprestimos = new ArrayList<>();
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        this.historicoEmprestimos.add(emprestimo);
    }

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Emprestimo> getHistoricoEmprestimos() {
        return historicoEmprestimos;
    }

    public void setHistoricoEmprestimos(List<Emprestimo> historicoEmprestimos) {
        this.historicoEmprestimos = historicoEmprestimos;
    }
}
