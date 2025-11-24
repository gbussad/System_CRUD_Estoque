package br.com.tp1.service;
import br.com.tp1.model.Produto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService implements IProdutoService {

    private final List<Produto> produtos = new ArrayList<>();

    @Override
    public void adicionar(Produto produto) {
        if (buscar(produto.id()).isPresent()) {
            throw new IllegalArgumentException("Já existe um produto com este ID: " + produto.id());
        }
        produtos.add(produto);
    }

    @Override
    public List<Produto> listarTodos() {
        return List.copyOf(produtos);
    }

    @Override
    public Optional<Produto> buscar(String id) {
        return produtos.stream().filter(p -> p.id().equals(id)).findFirst();
    }

    @Override
    public void atualizar(Produto produtoAtualizado) {
        deletar(produtoAtualizado.id());
        produtos.add(produtoAtualizado);
    }

    @Override
    public void deletar(String id) {
        boolean removeu = produtos.removeIf(p -> p.id().equals(id));
        if (!removeu) {
            throw new IllegalArgumentException("Produto não encontrado para exclusão: " + id);
        }
    }
}