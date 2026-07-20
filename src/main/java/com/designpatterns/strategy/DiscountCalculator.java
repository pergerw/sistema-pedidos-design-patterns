package com.designpatterns.strategy;

import com.designpatterns.model.Order;

public class DiscountCalculator {
    private DiscountStrategy strategy;

    public DiscountCalculator(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public double applyDiscount(Order order) {
        double discount = strategy.calculate(order);
        order.setDiscount(discount);
        return discount;
    }
}
