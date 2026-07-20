package com.designpatterns.singleton;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private static InventoryManager instance;
    private final Map<String, Integer> stock;

    private InventoryManager() {
        stock = new HashMap<>();
    }

    public static synchronized InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void addProduct(String productId, int quantity) {
        stock.put(productId, stock.getOrDefault(productId, 0) + quantity);
    }

    public int getStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }

    public void reduceStock(String productId, int quantity) {
        int current = stock.getOrDefault(productId, 0);
        stock.put(productId, Math.max(0, current - quantity));
    }

    public void displayStock() {
        System.out.println("\n[SINGLETON] Inventario atual:");
        stock.forEach((id, qty) ->
            System.out.printf("  Produto %s: %d unidades%n", id, qty));
    }
}
