package com.designpatterns.decorator;

import com.designpatterns.model.Order;

public abstract class OrderDecorator {
    protected Order order;

    public OrderDecorator(Order order) {
        this.order = order;
    }

    public abstract String getDescription();
    public abstract double getCost();
}
