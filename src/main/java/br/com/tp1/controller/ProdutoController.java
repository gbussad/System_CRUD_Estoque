package br.com.tp1.controller;

import br.com.tp1.model.Produto;
import br.com.tp1.service.IProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProdutoController {

    private final IProdutoService service;

    public ProdutoController(IProdutoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("produtos", service.listarTodos());
        return "listagem";
    }

    @GetMapping("/novo")
    public String paginaNovo() {
        return "formulario";
    }

    @PostMapping("/salvar")
    public String salvar(Produto produto) {
        service.adicionar(produto);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String paginaEditar(@PathVariable String id, Model model) {
        Produto produto = service.buscar(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("produto", produto);
        return "formulario-editar";
    }

    @PostMapping("/atualizar")
    public String atualizar(Produto produto) {
        service.atualizar(produto);
        return "redirect:/";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable String id) {
        service.deletar(id);
        return "redirect:/";
    }
}