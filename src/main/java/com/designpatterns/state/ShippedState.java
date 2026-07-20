package com.designpatterns.state;

import com.designpatterns.model.Order;
import com.designpatterns.model.OrderStatus;

public class ShippedState implements OrderState {
    @Override
    public void pay(Order order) {
        System.out.println("[STATE] ERRO: Pedido #" + order.getId() + " ja foi pago");
    }

    @Override
    public void ship(Order order) {
        System.out.println("[STATE] ERRO: Pedido #" + order.getId() + " ja foi enviado");
    }

    @Override
    public void deliver(Order order) {
        order.setStatus(OrderStatus.DELIVERED);
        System.out.println("[STATE] Pedido #" + order.getId() + " entregue com sucesso!");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("[STATE] ERRO: Pedido #" + order.getId() + " ja foi enviado, nao pode cancelar");
    }

    @Override
    public String getName() { return "ENVIADO"; }
}
