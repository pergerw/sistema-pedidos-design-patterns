package com.designpatterns.template;

import com.designpatterns.chain.OrderValidator;
import com.designpatterns.chain.PaymentValidator;
import com.designpatterns.model.Order;
import com.designpatterns.strategy.DiscountCalculator;
import com.designpatterns.strategy.PercentageDiscount;
import com.designpatterns.singleton.InventoryManager;

public class DigitalOrderProcessor extends OrderProcessor {

    @Override
    protected void validateOrder(Order order) {
        OrderValidator chain = new PaymentValidator();
        boolean valid = chain.validate(order);
        if (!valid) throw new RuntimeException("Pedido digital #" + order.getId() + " reprovado");
        System.out.println("[TEMPLATE] Pedido digital validado");
    }

    @Override
    protected void calculateDiscount(Order order) {
        DiscountCalculator calc = new DiscountCalculator(new PercentageDiscount(5));
        double discount = calc.applyDiscount(order);
        System.out.printf("[TEMPLATE] Desconto digital de R$%.2f (5%%)%n", discount);
    }

    @Override
    protected void applyShipping(Order order) {
        System.out.println("[TEMPLATE] Produto digital - sem frete");
    }

    @Override
    protected void processPayment(Order order) {
        order.setStatus(com.designpatterns.model.OrderStatus.PAID);
        System.out.printf("[TEMPLATE] Pagamento digital aprovado%n");
    }

    @Override
    protected void updateInventory(Order order) {
        System.out.println("[TEMPLATE] Produto digital - sem atualizacao de estoque fisico");
    }

    @Override
    protected void sendConfirmation(Order order) {
        System.out.printf("[TEMPLATE] Link de download enviado para %s%n", order.getCustomerEmail());
    }
}
