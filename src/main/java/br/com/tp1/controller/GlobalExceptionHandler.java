package br.com.tp1.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleValidacao(IllegalArgumentException e, Model model) {
        model.addAttribute("message", "Erro de Validação: " + e.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeral(Exception e, Model model) {
        model.addAttribute("message", "Ocorreu um erro inesperado no sistema. Tente novamente.");
        return "error";
    }
}