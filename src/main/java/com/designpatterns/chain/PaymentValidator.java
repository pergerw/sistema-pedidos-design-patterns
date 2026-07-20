package com.designpatterns.chain;

import com.designpatterns.model.Order;

public class PaymentValidator extends OrderValidator {
    @Override
    public boolean validate(Order order) {
        if (order.getTotal() <= 0) {
            System.out.println("[VALIDACAO] Total invalido para pagamento");
            return false;
        }
        System.out.printf("[VALIDACAO] Pagamento via %s - R$%.2f OK%n",
                order.getCustomerEmail(), order.getTotal());
        return validateNext(order);
    }
}
