package com.designpatterns.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String id;
    private String customerName;
    private String customerEmail;
    private String shippingAddress;
    private List<Product> products;
    private List<Integer> quantities;
    private double discount;
    private double extraCost;
    private OrderStatus status;

    public Order(String id, String customerName, String customerEmail, String shippingAddress) {
        this.id = id;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.shippingAddress = shippingAddress;
        this.products = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.discount = 0;
        this.extraCost = 0;
        this.status = OrderStatus.PENDING;
    }

    public String getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public String getShippingAddress() { return shippingAddress; }
    public List<Product> getProducts() { return products; }
    public List<Integer> getQuantities() { return quantities; }
    public double getDiscount() { return discount; }
    public double getExtraCost() { return extraCost; }
    public OrderStatus getStatus() { return status; }

    public void setStatus(OrderStatus status) { this.status = status; }
    public void setDiscount(double discount) { this.discount = discount; }
    public void setExtraCost(double extraCost) { this.extraCost = extraCost; }

    public void addProduct(Product product, int quantity) {
        this.products.add(product);
        this.quantities.add(quantity);
    }

    public double getSubtotal() {
        double total = 0;
        for (int i = 0; i < products.size(); i++) {
            total += products.get(i).getPrice() * quantities.get(i);
        }
        return total;
    }

    public double getTotal() {
        return getSubtotal() - discount + extraCost;
    }

    public void display() {
        System.out.println("+-----------------------------------------------+");
        System.out.printf("| Pedido #%-37s|\n", id);
        System.out.printf("| Cliente: %-35s|\n", customerName);
        System.out.println("+-----------------------------------------------+");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.printf("| %-3dx %-27s R$%6.2f |\n",
                    quantities.get(i), p.getName(), p.getPrice() * quantities.get(i));
        }
        System.out.println("+-----------------------------------------------+");
        System.out.printf("| Subtotal:               R$%8.2f      |\n", getSubtotal());
        if (discount > 0) System.out.printf("| Desconto:               -R$%8.2f     |\n", discount);
        if (extraCost > 0) System.out.printf("| Adicional:              +R$%8.2f     |\n", extraCost);
        System.out.printf("| Total:                  R$%8.2f      |\n", getTotal());
        System.out.printf("| Status: %-36s|\n", status);
        System.out.println("+-----------------------------------------------+");
    }
}
