package br.com.tp1;
import br.com.tp1.model.Produto;
import br.com.tp1.service.EstoqueService;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Random;

public class FuzzingTest {

    private final EstoqueService service = new EstoqueService();
    private final Random random = new Random();

    @Test
    public void testeDeEstresseFuzzing() {
        System.out.println("-> FUZZ TESTING (Tentativa de quebrar o sistema com dados aleatórios) ");

        int sucessos = 0;
        int bloqueios = 0;

        for (int i = 0; i < 1000; i++) {
            String idAleatorio = String.valueOf(random.nextInt());
            String nomeAleatorio = gerarStringAleatoria();
            BigDecimal precoAleatorio = new BigDecimal(random.nextInt(10000) - 5000);
            int qtdAleatoria = random.nextInt(200) - 100;

            try {
                Produto p = new Produto(idAleatorio, nomeAleatorio, precoAleatorio, qtdAleatoria);
                service.adicionar(p);
                sucessos++;
            } catch (IllegalArgumentException e) {
                bloqueios++;
            } catch (Exception e) {
                throw new RuntimeException(" O sistema quebrou com a entrada: " + nomeAleatorio, e);
            }
        }

        System.out.println("-> RELATÓRIO FUZZING ");
        System.out.println("Inserções Válidas: " + sucessos);
        System.out.println("Bloqueios de Segurança (Dados Inválidos): " + bloqueios);
        System.out.println(" Nenhuma falha crítica detectada. ");
    }

    private String gerarStringAleatoria() {
        byte[] array = new byte[random.nextInt(20) + 1];
        random.nextBytes(array);
        return new String(array);
    }
}