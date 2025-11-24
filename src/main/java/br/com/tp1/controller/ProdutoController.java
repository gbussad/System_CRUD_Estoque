package br.com.tp1.controller;

import br.com.tp1.model.Produto;
import br.com.tp1.service.EstoqueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProdutoController {

    private final EstoqueService service;

    public ProdutoController(EstoqueService service) {
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
}