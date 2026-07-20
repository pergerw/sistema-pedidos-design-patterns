package com.designpatterns.template;

import com.designpatterns.model.Order;

public abstract class OrderProcessor {

    public final void process(Order order) {
        System.out.println("\n===============================================");
        System.out.println("  PROCESSANDO PEDIDO #" + order.getId());
        System.out.println("===============================================");

        validateOrder(order);
        calculateDiscount(order);
        applyShipping(order);
        processPayment(order);
        updateInventory(order);
        sendConfirmation(order);

        System.out.println("===============================================\n");
    }

    protected abstract void validateOrder(Order order);
    protected abstract void calculateDiscount(Order order);
    protected abstract void applyShipping(Order order);
    protected abstract void processPayment(Order order);
    protected abstract void updateInventory(Order order);
    protected abstract void sendConfirmation(Order order);
}
