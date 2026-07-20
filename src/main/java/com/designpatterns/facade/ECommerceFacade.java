package com.designpatterns.facade;

import com.designpatterns.decorator.ExpressShippingDecorator;
import com.designpatterns.decorator.GiftWrapDecorator;
import com.designpatterns.decorator.InsuranceDecorator;
import com.designpatterns.factory.PaymentFactory;
import com.designpatterns.factory.PaymentFactory.PaymentMethod;
import com.designpatterns.model.Order;
import com.designpatterns.model.Product;
import com.designpatterns.singleton.InventoryManager;
import com.designpatterns.state.OrderStateMachine;
import com.designpatterns.state.OrderStateMachine.OrderAction;
import com.designpatterns.template.DigitalOrderProcessor;
import com.designpatterns.template.OrderProcessor;
import com.designpatterns.template.PhysicalOrderProcessor;

public class ECommerceFacade {
    private final InventoryManager inventory;
    private final OrderStateMachine stateMachine;
    private int orderCounter;

    public ECommerceFacade() {
        this.inventory = InventoryManager.getInstance();
        this.stateMachine = new OrderStateMachine();
        this.orderCounter = 0;
    }

    public void initStock() {
        inventory.addProduct("P001", 50);
        inventory.addProduct("P002", 30);
        inventory.addProduct("P003", 100);
        inventory.addProduct("P004", 999999);
        System.out.println("[FACADE] Estoque inicializado");
        inventory.displayStock();
    }

    public Product getProduct(String id, String name, double price, String category, int stock) {
        return new Product(id, name, price, category, stock);
    }

    public Order createOrder(String customerName, String customerEmail, String address) {
        orderCounter++;
        String orderId = "ORD-" + String.format("%04d", orderCounter);
        Order order = new Order(orderId, customerName, customerEmail, address);
        System.out.printf("[FACADE] Pedido #%s criado para %s%n", orderId, customerName);
        return order;
    }

    public void addItem(Order order, Product product, int quantity) {
        order.addProduct(product, quantity);
        System.out.printf("[FACADE] Adicionado %dx %s ao pedido #%s%n",
                quantity, product.getName(), order.getId());
    }

    public void addGiftWrap(Order order) {
        new GiftWrapDecorator(order).apply();
    }

    public void addInsurance(Order order) {
        new InsuranceDecorator(order).apply();
    }

    public void addExpressShipping(Order order) {
        new ExpressShippingDecorator(order).apply();
    }

    public void processOrder(Order order, String paymentType, boolean isDigital) {
        PaymentMethod payment = PaymentFactory.create(paymentType);

        OrderProcessor processor = isDigital
                ? new DigitalOrderProcessor()
                : new PhysicalOrderProcessor();

        try {
            processor.process(order);
            payment.pay(order.getTotal());
            order.display();

            stateMachine.execute(order, OrderAction.SHIP);
            if (!isDigital) {
                stateMachine.execute(order, OrderAction.DELIVER);
            }
        } catch (RuntimeException e) {
            System.err.println("[FACADE] ERRO: " + e.getMessage());
            stateMachine.execute(order, OrderAction.CANCEL);
        }
    }

    public void showStock() {
        inventory.displayStock();
    }
}
