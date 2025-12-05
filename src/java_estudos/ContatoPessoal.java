package java_estudos;

public class ContatoPessoal extends Contato {
    private String apelido;

    public ContatoPessoal(String nome, String telefone, String apelido) {
        super(nome, telefone);
        setApelido(apelido);
    }

    @Override
    void exibirDetalhes() {
        if (apelido != null && !apelido.isBlank()) {
            System.out.println("Nome: " + getNome() + " (" + getApelido() + ")");
        } else {
            System.out.println("Nome: " + getNome());
        }
        System.out.println("Telefone: " + getTelefone());
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
}
