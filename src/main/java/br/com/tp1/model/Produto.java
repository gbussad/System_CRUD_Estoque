package br.com.tp1.model;
import java.math.BigDecimal;
import java.util.Objects;

public record Produto(
        String id,
        String nome,
        BigDecimal preco,
        int quantidade
) {
    public Produto {
        Objects.requireNonNull(id, "O ID é obrigatório");
        Objects.requireNonNull(nome, "O Nome é obrigatório");

        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa.");
        }
    }
}