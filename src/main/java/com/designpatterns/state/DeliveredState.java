package com.designpatterns.state;

import com.designpatterns.model.Order;
import com.designpatterns.model.OrderStatus;

public class DeliveredState implements OrderState {
    @Override
    public void pay(Order order) {
        System.out.println("[STATE] ERRO: Pedido #" + order.getId() + " ja foi entregue");
    }

    @Override
    public void ship(Order order) {
        System.out.println("[STATE] ERRO: Pedido #" + order.getId() + " ja foi entregue");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("[STATE] ERRO: Pedido #" + order.getId() + " ja foi entregue");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("[STATE] ERRO: Pedido #" + order.getId() + " ja foi entregue, nao pode cancelar");
    }

    @Override
    public String getName() { return "ENTREGUE"; }
}
