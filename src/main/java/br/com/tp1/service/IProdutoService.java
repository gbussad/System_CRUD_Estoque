package br.com.tp1.service;

import br.com.tp1.model.Produto;
import java.util.List;
import java.util.Optional;

public interface IProdutoService {
    void adicionar(Produto produto);
    List<Produto> listarTodos();
    Optional<Produto> buscar(String id);
    void atualizar(Produto produto);
    void deletar(String id);
}