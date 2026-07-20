package com.designpatterns.decorator;

import com.designpatterns.model.Order;

public class GiftWrapDecorator extends OrderDecorator {
    private static final double GIFT_WRAP_COST = 9.90;

    public GiftWrapDecorator(Order order) {
        super(order);
    }

    @Override
    public String getDescription() {
        return "Embrulho para presente";
    }

    @Override
    public double getCost() {
        return GIFT_WRAP_COST;
    }

    public void apply() {
        order.setExtraCost(order.getExtraCost() + getCost());
        System.out.printf("[DECORATOR] + %s: R$%.2f%n", getDescription(), getCost());
    }
}
