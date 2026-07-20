package com.designpatterns.state;

import com.designpatterns.model.Order;
import com.designpatterns.model.OrderStatus;

public class CancelledState implements OrderState {
    @Override
    public void pay(Order order) {
        System.out.println("[STATE] ERRO: Pedido #" + order.getId() + " foi cancelado");
    }

    @Override
    public void ship(Order order) {
        System.out.println("[STATE] ERRO: Pedido #" + order.getId() + " foi cancelado");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("[STATE] ERRO: Pedido #" + order.getId() + " foi cancelado");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("[STATE] ERRO: Pedido #" + order.getId() + " ja foi cancelado");
    }

    @Override
    public String getName() { return "CANCELADO"; }
}
