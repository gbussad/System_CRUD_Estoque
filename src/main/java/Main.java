import br.com.tp1.model.Produto;
import br.com.tp1.service.EstoqueService;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Sistema de Estoque (TP1) ");
        EstoqueService service = new EstoqueService();

        try {
            Produto p1 = new Produto("1", "Teclado Mecânico", new BigDecimal("250.00"), 10);
            service.adicionar(p1);
            System.out.println("Produto adicionado: " + p1.nome());
        } catch (Exception e) {
            System.out.println("[ERRO] " + e.getMessage());
        }

        System.out.println("\n Testando Validação de Preço Negativo ");
        try {
            Produto pErro = new Produto("2", "Mouse Quebrado", new BigDecimal("-50.00"), 5);
            service.adicionar(pErro);
        } catch (IllegalArgumentException e) {
            System.out.println("O sistema bloqueou corretamente: " + e.getMessage());
        }

        System.out.println("\n Estoque Atual: ");
        service.listarTodos().forEach(System.out::println);
    }
}