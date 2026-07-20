package com.designpatterns.state;

import com.designpatterns.model.Order;

public interface OrderState {
    void pay(Order order);
    void ship(Order order);
    void deliver(Order order);
    void cancel(Order order);
    String getName();
}
