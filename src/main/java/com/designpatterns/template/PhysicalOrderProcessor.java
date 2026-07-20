package com.designpatterns.template;

import com.designpatterns.chain.AddressValidator;
import com.designpatterns.chain.OrderValidator;
import com.designpatterns.chain.PaymentValidator;
import com.designpatterns.chain.StockValidator;
import com.designpatterns.model.Order;
import com.designpatterns.singleton.InventoryManager;
import com.designpatterns.strategy.LoyaltyDiscount;
import com.designpatterns.strategy.DiscountCalculator;

public class PhysicalOrderProcessor extends OrderProcessor {

    @Override
    protected void validateOrder(Order order) {
        OrderValidator chain = new StockValidator();
        chain.linkWith(new PaymentValidator())
             .linkWith(new AddressValidator());
        boolean valid = chain.validate(order);
        if (!valid) throw new RuntimeException("Pedido #" + order.getId() + " reprovado na validacao");
        System.out.println("[TEMPLATE] Pedido validado com sucesso");
    }

    @Override
    protected void calculateDiscount(Order order) {
        DiscountCalculator calc = new DiscountCalculator(new LoyaltyDiscount());
        double discount = calc.applyDiscount(order);
        System.out.printf("[TEMPLATE] Desconto de R$%.2f aplicado (10%% fidelidade)%n", discount);
    }

    @Override
    protected void applyShipping(Order order) {
        double shipping = order.getSubtotal() > 100 ? 0 : 15.90;
        order.setExtraCost(order.getExtraCost() + shipping);
        System.out.printf("[TEMPLATE] Frete: %s (R$%.2f)%n",
                shipping == 0 ? "GRATIS" : "Taxa fixa", shipping);
    }

    @Override
    protected void processPayment(Order order) {
        order.setStatus(com.designpatterns.model.OrderStatus.PAID);
        System.out.printf("[TEMPLATE] Pagamento aprovado via %s%n", order.getCustomerEmail());
    }

    @Override
    protected void updateInventory(Order order) {
        InventoryManager inventory = InventoryManager.getInstance();
        for (int i = 0; i < order.getProducts().size(); i++) {
            inventory.reduceStock(order.getProducts().get(i).getId(),
                    order.getQuantities().get(i));
        }
        System.out.println("[TEMPLATE] Estoque atualizado");
    }

    @Override
    protected void sendConfirmation(Order order) {
        System.out.printf("[TEMPLATE] E-mail de confirmacao enviado para %s%n", order.getCustomerEmail());
    }
}
