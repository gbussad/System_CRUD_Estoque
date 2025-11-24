package br.com.tp1.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EstoquePage {

    private final WebDriver driver;

    public EstoquePage(WebDriver driver) {
        this.driver = driver;
    }

    public void visitar() {
        driver.get("http://localhost:8080/");
    }

    public void clicarEmNovo() {
        driver.findElement(By.id("btn-novo")).click();
    }

    public void cadastrarProduto(String id, String nome, String preco, String qtd) {
        driver.findElement(By.id("id")).sendKeys(id);
        driver.findElement(By.id("nome")).sendKeys(nome);
        driver.findElement(By.id("preco")).sendKeys(preco);
        driver.findElement(By.id("quantidade")).sendKeys(qtd);
        driver.findElement(By.id("btn-salvar")).click();
    }

    public boolean existeNaTabela(String nomeEsperado) {
        List<WebElement> colunas = driver.findElements(By.tagName("td"));
        return colunas.stream()
                .anyMatch(elemento -> elemento.getText().equals(nomeEsperado));
    }
}