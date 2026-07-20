package com.designpatterns.strategy;

import com.designpatterns.model.Order;

@FunctionalInterface
public interface DiscountStrategy {
    double calculate(Order order);
}
