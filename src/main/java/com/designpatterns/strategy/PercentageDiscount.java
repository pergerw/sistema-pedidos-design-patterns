package com.designpatterns.strategy;

import com.designpatterns.model.Order;

public class PercentageDiscount implements DiscountStrategy {
    private final double percentage;

    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double calculate(Order order) {
        return order.getSubtotal() * percentage / 100;
    }
}
