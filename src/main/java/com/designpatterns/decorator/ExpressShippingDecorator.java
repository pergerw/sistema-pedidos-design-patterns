package com.designpatterns.decorator;

import com.designpatterns.model.Order;

public class ExpressShippingDecorator extends OrderDecorator {
    private static final double EXPRESS_COST = 25.00;

    public ExpressShippingDecorator(Order order) {
        super(order);
    }

    @Override
    public String getDescription() {
        return "Entrega expressa (24h)";
    }

    @Override
    public double getCost() {
        return EXPRESS_COST;
    }

    public void apply() {
        order.setExtraCost(order.getExtraCost() + getCost());
        System.out.printf("[DECORATOR] + %s: R$%.2f%n", getDescription(), getCost());
    }
}
