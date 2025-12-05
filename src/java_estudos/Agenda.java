package java_estudos;

import java.util.ArrayList;

public class Agenda {
    private ArrayList<Contato> meusContatos;

    public Agenda() {
        this.meusContatos = new ArrayList<>();
    }

    public void adicionarContato(Contato c) {
        this.meusContatos.add(c);
        System.out.println("Contato salvo com sucesso!");
    }

    public void listarContatos() {
        if (meusContatos.isEmpty()) {
            System.out.println("Agenda vazia.");
            return;
        } else {
            System.out.println("--- Lista de Contatos ---");
            for (Contato c : meusContatos) {
                c.exibirDetalhes();
                System.out.println("-------------------------");
            }
        }
    }

    public void removerContato(String nomeParaRemover) {
        Contato contatoEncontrado = null;

        for (Contato c : meusContatos) {
            if (c.getNome().equalsIgnoreCase(nomeParaRemover)) {
                contatoEncontrado = c;
                break;
            }
        }

        if (contatoEncontrado != null) {
            meusContatos.remove(contatoEncontrado);
            System.out.println("Contato de " + contatoEncontrado.getNome() + " removido com sucesso.");
        } else {
            System.out.println("Contato não encontrado na agenda.");
        }
    }
}
