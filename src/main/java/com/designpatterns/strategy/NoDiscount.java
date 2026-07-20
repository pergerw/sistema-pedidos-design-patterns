package com.designpatterns.strategy;

import com.designpatterns.model.Order;

public class NoDiscount implements DiscountStrategy {
    @Override
    public double calculate(Order order) {
        return 0;
    }
}
