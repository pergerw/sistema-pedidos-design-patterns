package com.designpatterns.state;

import com.designpatterns.model.Order;
import com.designpatterns.model.OrderStatus;

public class PaidState implements OrderState {
    @Override
    public void pay(Order order) {
        System.out.println("[STATE] ERRO: Pedido #" + order.getId() + " ja foi pago");
    }

    @Override
    public void ship(Order order) {
        order.setStatus(OrderStatus.SHIPPED);
        System.out.println("[STATE] Pedido #" + order.getId() + " enviado");
    }

    @Override
    public void deliver(Order order) {
        System.out.println("[STATE] ERRO: Pedido #" + order.getId() + " ainda nao foi enviado");
    }

    @Override
    public void cancel(Order order) {
        order.setStatus(OrderStatus.CANCELLED);
        System.out.println("[STATE] Pedido #" + order.getId() + " cancelado (reembolso sera processado)");
    }

    @Override
    public String getName() { return "PAGO"; }
}
