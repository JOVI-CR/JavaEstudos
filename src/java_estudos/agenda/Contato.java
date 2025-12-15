package java_estudos.agenda;

public abstract class Contato {
    private String nome, telefone;

    public Contato(String nome, String telefone) {
        setNome(nome) ;
        setTelefone(telefone);
    }
    abstract void exibirDetalhes();

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.length() < 8) {
            throw new IllegalArgumentException("Telefone inválido! Deve ter pelo menos 8 digitos.");
        }
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
