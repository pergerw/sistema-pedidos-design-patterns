package com.designpatterns.factory;

public class PaymentFactory {

    public static PaymentMethod create(String type) {
        return switch (type.toUpperCase()) {
            case "CREDIT_CARD" -> new CreditCardPayment();
            case "PIX"         -> new PixPayment();
            case "BOLETO"      -> new BoletoPayment();
            default -> throw new IllegalArgumentException("Tipo de pagamento invalido: " + type);
        };
    }

    public interface PaymentMethod {
        void pay(double amount);
        String getName();
    }

    private static class CreditCardPayment implements PaymentMethod {
        @Override
        public void pay(double amount) {
            System.out.printf("[FACTORY] Pagamento de R$%.2f no Cartao de Credito aprovado%n", amount);
        }
        @Override
        public String getName() { return "Cartao de Credito"; }
    }

    private static class PixPayment implements PaymentMethod {
        @Override
        public void pay(double amount) {
            System.out.printf("[FACTORY] Pagamento de R$%.2f via PIX aprovado (instantaneo)%n", amount);
        }
        @Override
        public String getName() { return "PIX"; }
    }

    private static class BoletoPayment implements PaymentMethod {
        @Override
        public void pay(double amount) {
            System.out.printf("[FACTORY] Boleto de R$%.2f gerado (vence em 3 dias uteis)%n", amount);
        }
        @Override
        public String getName() { return "Boleto"; }
    }
}
