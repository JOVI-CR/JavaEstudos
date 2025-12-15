package java_estudos.agenda;

public class ContatoComercial extends Contato {
    private String nomeDaEmpresa;

    public ContatoComercial(String nome, String telefone, String nomeDaEmpresa) {
        super(nome, telefone);
        setNomeDaEmpresa(nomeDaEmpresa);
    }

    @Override
    void exibirDetalhes() {
        System.out.println("Nome: " + getNome());
        System.out.println("Nome da Empresa: " + getNomeDaEmpresa());
        System.out.println("Tel: " + getTelefone());
    }

    public String getNomeDaEmpresa() {
        return nomeDaEmpresa;
    }

    public void setNomeDaEmpresa(String nomeDaEmpresa) {
        this.nomeDaEmpresa = nomeDaEmpresa;
    }
}
