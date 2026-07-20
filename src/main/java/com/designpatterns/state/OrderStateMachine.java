package com.designpatterns.state;

import com.designpatterns.model.Order;
import com.designpatterns.model.OrderStatus;
import java.util.Map;

public class OrderStateMachine {
    private final Map<OrderStatus, OrderState> states = Map.of(
        OrderStatus.PENDING,   new PendingState(),
        OrderStatus.PAID,      new PaidState(),
        OrderStatus.SHIPPED,   new ShippedState(),
        OrderStatus.DELIVERED, new DeliveredState(),
        OrderStatus.CANCELLED, new CancelledState()
    );

    public void execute(Order order, OrderAction action) {
        OrderState state = states.get(order.getStatus());
        System.out.println("\n[STATEMACHINE] Acao: " + action + " | Estado atual: " + state.getName());
        switch (action) {
            case PAY     -> state.pay(order);
            case SHIP    -> state.ship(order);
            case DELIVER -> state.deliver(order);
            case CANCEL  -> state.cancel(order);
        }
    }

    public enum OrderAction { PAY, SHIP, DELIVER, CANCEL }
}
