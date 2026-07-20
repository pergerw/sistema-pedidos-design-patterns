package com.designpatterns.chain;

import com.designpatterns.model.Order;

public abstract class OrderValidator {
    protected OrderValidator next;

    public OrderValidator linkWith(OrderValidator next) {
        this.next = next;
        return next;
    }

    public abstract boolean validate(Order order);

    protected boolean validateNext(Order order) {
        if (next == null) return true;
        return next.validate(order);
    }
}
