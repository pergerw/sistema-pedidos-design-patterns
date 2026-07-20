package com.designpatterns.model;

public class Product {
    private String id;
    private String name;
    private double price;
    private String category;
    private int stockQuantity;

    public Product(String id, String name, double price, String category, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getStockQuantity() { return stockQuantity; }

    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    @Override
    public String toString() {
        return String.format("%s (%s) - R$%.2f [estoque: %d]", name, category, price, stockQuantity);
    }
}
