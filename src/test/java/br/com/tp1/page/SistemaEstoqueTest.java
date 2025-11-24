package br.com.tp1.page;

import br.com.tp1.EstoqueApplication;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = EstoqueApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
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
        options.addArguments("--start-maximized");

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
        pagina.cadastrarProduto("555", "Produto CI/CD", "99.90", "2");

        boolean achou = pagina.existeNaTabela("Produto CI/CD");
        assertTrue(achou, "O produto deveria aparecer na tabela (Teste Headless)!");
    }
}