package br.com.tp1.service;
import br.com.tp1.model.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EstoqueService {
    private final List<Produto> produtos = new ArrayList<>();
    public void adicionar(Produto produto) {
        boolean existe = produtos.stream().anyMatch(p -> p.id(

        ).equals(produto.id()));
        if (existe) {
            throw new IllegalArgumentException("Já existe um produto com este ID: " +
                    produto.id());
        }
        produtos.add(produto);
    }
    public List<Produto> listarTodos() {
        return List.copyOf(produtos);
    }
    public Optional<Produto> buscar(String id) {
        return produtos.stream().filter(p -> p.id().equals(id)).findFirst();
    }
    public void deletar(String id) {
        produtos.removeIf(p -> p.id().equals(id));
    }
}