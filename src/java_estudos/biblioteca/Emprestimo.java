package java_estudos.biblioteca;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private Usuario usuario;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private Livro livro;

    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataRetirada = LocalDate.now();
        this.dataDevolucao = dataRetirada.plusDays(7);
    }

    public boolean estaAtrasado() {
        LocalDate hoje = LocalDate.now();
        return hoje.isAfter(this.dataDevolucao);
    }

    public double calcularMulta() {
        if (!estaAtrasado()) {
            return 0.0;
        } else {
            LocalDate hoje = LocalDate.now();

            long diasDeAtraso = ChronoUnit.DAYS.between(this.dataDevolucao, hoje);
            double valorPorDia = 10.00;
            return diasDeAtraso * valorPorDia;
        }
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
