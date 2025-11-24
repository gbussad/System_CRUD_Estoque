package br.com.tp1.page;

import br.com.tp1.page.EstoquePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SistemaEstoqueTest {

    private WebDriver driver;
    private EstoquePage pagina;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless=new");

        driver = new ChromeDriver(options);
        pagina = new EstoquePage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void deveCadastrarProdutoComSucesso() {
        pagina.visitar();
        pagina.clicarEmNovo();
        pagina.cadastrarProduto("999", "Notebook Ultra Teste", "5000.00", "10");

        boolean achou = pagina.existeNaTabela("Notebook Ultra Teste");
        assertTrue(achou, "O produto cadastrado deveria aparecer na tabela!");
    }
}