package com.designpatterns.chain;

import com.designpatterns.model.Order;
import com.designpatterns.model.Product;
import com.designpatterns.singleton.InventoryManager;

public class StockValidator extends OrderValidator {
    @Override
    public boolean validate(Order order) {
        InventoryManager inventory = InventoryManager.getInstance();
        for (int i = 0; i < order.getProducts().size(); i++) {
            Product product = order.getProducts().get(i);
            int requested = order.getQuantities().get(i);
            if (inventory.getStock(product.getId()) < requested) {
                System.out.printf("[VALIDACAO] Estoque insuficiente para %s (solicitado: %d, disponivel: %d)%n",
                        product.getName(), requested, inventory.getStock(product.getId()));
                return false;
            }
        }
        System.out.println("[VALIDACAO] Estoque OK");
        return validateNext(order);
    }
}
