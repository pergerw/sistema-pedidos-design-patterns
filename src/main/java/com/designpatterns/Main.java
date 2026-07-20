package com.designpatterns;

import com.designpatterns.facade.ECommerceFacade;
import com.designpatterns.model.Order;
import com.designpatterns.model.Product;
import com.designpatterns.state.OrderStateMachine;
import com.designpatterns.state.OrderStateMachine.OrderAction;
import com.designpatterns.strategy.BlackFridayDiscount;
import com.designpatterns.strategy.DiscountCalculator;
import com.designpatterns.strategy.PercentageDiscount;

public class Main {
    public static void main(String[] args) {
        System.out.println("=" .repeat(55));
        System.out.println("  DESIGN PATTERNS - E-COMMERCE SIMULATOR");
        System.out.println("=" .repeat(55));

        ECommerceFacade ecommerce = new ECommerceFacade();
        ecommerce.initStock();

        Product notebook = ecommerce.getProduct("P001", "Notebook Dell", 4500.00, "Eletronicos", 50);
        Product mouse = ecommerce.getProduct("P002", "Mouse Gamer", 250.00, "Perifericos", 30);
        Product curso = ecommerce.getProduct("P004", "Curso Java", 97.00, "Digital", 999999);

        // ===== DEMONSTRACAO 1: Strategy =====
        System.out.println("\n" + "#".repeat(55));
        System.out.println("  #1 STRATEGY - Calculo de descontos");
        System.out.println("#".repeat(55));

        Order orderStrategy = ecommerce.createOrder("Joao", "joao@email.com", "Rua A, 123");
        orderStrategy.addProduct(notebook, 1);
        orderStrategy.addProduct(mouse, 2);

        DiscountCalculator calc = new DiscountCalculator(new PercentageDiscount(10));
        double d1 = calc.applyDiscount(orderStrategy);
        System.out.printf("Desconto 10%%: R$%.2f%n", d1);

        calc.setStrategy(new BlackFridayDiscount());
        double d2 = calc.applyDiscount(orderStrategy);
        System.out.printf("Desconto Black Friday (50%%): R$%.2f%n", d2);

        calc.setStrategy(order -> order.getSubtotal() > 100 ? 20 : 0);
        double d3 = calc.applyDiscount(orderStrategy);
        System.out.printf("Desconto customizado (lambda): R$%.2f%n", d3);

        // ===== DEMONSTRACAO 2: Pedido Fisico =====
        System.out.println("\n" + "#".repeat(55));
        System.out.println("  #2 CHAIN + TEMPLATE + DECORATOR + FACTORY");
        System.out.println("  Pedido Fisico Completo");
        System.out.println("#".repeat(55));

        Order order1 = ecommerce.createOrder("Maria Silva", "maria@email.com", "Av Paulista, 1000");
        ecommerce.addItem(order1, notebook, 1);
        ecommerce.addItem(order1, mouse, 1);
        ecommerce.addGiftWrap(order1);
        ecommerce.addInsurance(order1);
        ecommerce.processOrder(order1, "PIX", false);

        // ===== DEMONSTRACAO 3: Pedido Digital =====
        System.out.println("\n" + "#".repeat(55));
        System.out.println("  #3 TEMPLATE - Pedido Digital");
        System.out.println("#".repeat(55));

        Order order2 = ecommerce.createOrder("Carlos Oliveira", "carlos@email.com", "");
        ecommerce.addItem(order2, curso, 2);
        ecommerce.processOrder(order2, "CREDIT_CARD", true);

        // ===== DEMONSTRACAO 4: State Machine =====
        System.out.println("\n" + "#".repeat(55));
        System.out.println("  #4 STATE - Maquina de estados");
        System.out.println("#".repeat(55));

        Order order3 = ecommerce.createOrder("Ana Costa", "ana@email.com", "Rua B, 500");
        ecommerce.addItem(order3, mouse, 3);
        ecommerce.addExpressShipping(order3);

        OrderStateMachine sm = new OrderStateMachine();
        System.out.println("\n--- Fluxo normal ---");
        order3.setDiscount(order3.getSubtotal() * 0.1);
        sm.execute(order3, OrderAction.PAY);
        sm.execute(order3, OrderAction.SHIP);
        sm.execute(order3, OrderAction.DELIVER);

        System.out.println("\n--- Transicoes invalidas (DEPOIS de entregue) ---");
        sm.execute(order3, OrderAction.PAY);
        sm.execute(order3, OrderAction.SHIP);
        sm.execute(order3, OrderAction.CANCEL);

        System.out.println("\n--- Cancelamento no estado PENDENTE ---");
        Order order4 = ecommerce.createOrder("Pedro Santos", "pedro@email.com", "Rua C, 200");
        ecommerce.addItem(order4, notebook, 1);
        sm.execute(order4, OrderAction.CANCEL);
        sm.execute(order4, OrderAction.PAY);

        // ===== RESUMO FINAL =====
        System.out.println("\n" .repeat(1));
        System.out.println("=" .repeat(55));
        System.out.println("  RESUMO DOS PADROES UTILIZADOS");
        System.out.println("=" .repeat(55));
        System.out.println("""
                1. STRATEGY       - Algoritmos de desconto intercambiaveis
                2. CHAIN OF       - Validacao em cadeia (estoque -> pagamento -> endereco)
                   RESPONSIBILITY
                3. TEMPLATE       - Processamento de pedido com etapas fixas
                   METHOD
                4. DECORATOR      - Adicao dinamica de funcionalidades (embrulho, seguro)
                5. STATE          - Maquina de estados para ciclo de vida do pedido
                6. FACADE         - Interface simplificada para o subsistema
                7. SINGLETON      - Gerenciamento unico de inventario
                8. FACTORY        - Criacao de metodos de pagamento
                """);

        ecommerce.showStock();
    }
}
