package com.designpatterns.chain;

import com.designpatterns.model.Order;

public class AddressValidator extends OrderValidator {
    @Override
    public boolean validate(Order order) {
        String address = order.getShippingAddress();
        if (address == null || address.trim().isEmpty()) {
            System.out.println("[VALIDACAO] Endereco de entrega invalido");
            return false;
        }
        if (!address.contains(",")) {
            System.out.println("[VALIDACAO] Endereco incompleto (falta numero)");
            return false;
        }
        System.out.println("[VALIDACAO] Endereco OK");
        return validateNext(order);
    }
}
