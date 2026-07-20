package com.designpatterns.strategy;

import com.designpatterns.model.Order;

public class LoyaltyDiscount implements DiscountStrategy {
    @Override
    public double calculate(Order order) {
        return order.getSubtotal() * 0.10;
    }
}
