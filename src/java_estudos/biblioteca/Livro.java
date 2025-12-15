package java_estudos.biblioteca;

import java.util.UUID;

public class Livro {

    private String titulo;
    private String autor;
    private String livroID;
    private boolean disponivel;

    public Livro(String titulo, String autor) {
        this.livroID = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }



    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getLivroID() {
        return livroID;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public boolean emprestar() {
        if (this.disponivel) {
            this.disponivel = false;
            return true;
        } else {
            return false;
        }
    }

    public void devolver() {
        this.disponivel = true;
    }



}
