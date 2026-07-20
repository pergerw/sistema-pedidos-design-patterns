package com.designpatterns.decorator;

import com.designpatterns.model.Order;

public class InsuranceDecorator extends OrderDecorator {
    private static final double INSURANCE_COST = 5.50;

    public InsuranceDecorator(Order order) {
        super(order);
    }

    @Override
    public String getDescription() {
        return "Seguro de entrega";
    }

    @Override
    public double getCost() {
        return INSURANCE_COST;
    }

    public void apply() {
        order.setExtraCost(order.getExtraCost() + getCost());
        System.out.printf("[DECORATOR] + %s: R$%.2f%n", getDescription(), getCost());
    }
}
