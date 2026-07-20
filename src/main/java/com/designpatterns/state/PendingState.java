package com.designpatterns.state;

import com.designpatterns.model.Order;
import com.designpatterns.model.OrderStatus;

public class PendingState implements OrderState {
    @Override
    public void pay(Order order) {
        order.setStatus(OrderStatus.PAID);
        System.out.println("[STATE] Pedido #" + order.getId() + " pago com sucesso");
    }

    @Override
    public void ship(Order order) {
        System.out.println("[STATE] ERRO: Pedido #" + order.getId() + " ainda nao foi pago");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("[STATE] ERRO: Pedido #" + order.getId() + " ainda nao foi pago");
    }

    @Override
    public void cancel(Order order) {
        order.setStatus(OrderStatus.CANCELLED);
        System.out.println("[STATE] Pedido #" + order.getId() + " cancelado");
    }

    @Override
    public String getName() { return "PENDENTE"; }
}
