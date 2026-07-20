# Sistema de Pedidos - Design Patterns em Java

Projeto educacional demonstrando 8 padrões de projeto (Design Patterns) aplicados em um sistema de e-commerce com Java puro.

## Padrões Implementados

| Padrão | Categoria | O que faz |
|--------|-----------|-----------|
| **Strategy** | Comportamental | Algoritmos de desconto intercambiáveis (fidelidade, Black Friday, percentual) |
| **Chain of Responsibility** | Comportamental | Validação em cadeia: estoque → pagamento → endereço |
| **Template Method** | Comportamental | Processamento de pedidos físicos e digitais com etapas fixas |
| **Decorator** | Estrutural | Adiciona funcionalidades dinamicamente (embrulho, seguro, frete expresso) |
| **State** | Comportamental | Máquina de estados: PENDENTE → PAGO → ENVIADO → ENTREGUE |
| **Facade** | Estrutural | Interface simplificada (`ECommerceFacade`) para todo o subsistema |
| **Singleton** | Criacional | Instância única de gerenciamento de inventário |
| **Factory** | Criacional | Criação de métodos de pagamento (Cartão, PIX, Boleto) |

## Pré-requisitos

- Java 17+
- Maven (opcional, pode compilar com javac)

## Como executar

### Com javac

```bash
javac -encoding utf-8 -d build src/main/java/com/designpatterns/**/*.java
java -cp build com.designpatterns.Main
```

### Com Maven

```bash
mvn compile exec:java -Dexec.mainClass="com.designpatterns.Main"
```

## Estrutura do projeto

```
src/main/java/com/designpatterns/
├── Main.java                  # Demonstração de todos os padrões
├── model/                     # Modelos de domínio
│   ├── Order.java
│   ├── OrderStatus.java
│   └── Product.java
├── strategy/                  # Padrão Strategy
│   ├── DiscountStrategy.java
│   ├── NoDiscount.java
│   ├── PercentageDiscount.java
│   ├── LoyaltyDiscount.java
│   ├── BlackFridayDiscount.java
│   └── DiscountCalculator.java
├── chain/                     # Padrão Chain of Responsibility
│   ├── OrderValidator.java
│   ├── StockValidator.java
│   ├── PaymentValidator.java
│   └── AddressValidator.java
├── template/                  # Padrão Template Method
│   ├── OrderProcessor.java
│   ├── PhysicalOrderProcessor.java
│   └── DigitalOrderProcessor.java
├── decorator/                 # Padrão Decorator
│   ├── OrderDecorator.java
│   ├── GiftWrapDecorator.java
│   ├── InsuranceDecorator.java
│   └── ExpressShippingDecorator.java
├── state/                     # Padrão State
│   ├── OrderState.java
│   ├── PendingState.java
│   ├── PaidState.java
│   ├── ShippedState.java
│   ├── DeliveredState.java
│   ├── CancelledState.java
│   └── OrderStateMachine.java
├── facade/                    # Padrão Facade
│   └── ECommerceFacade.java
├── singleton/                 # Padrão Singleton
│   └── InventoryManager.java
└── factory/                   # Padrão Factory
    └── PaymentFactory.java
```

## Funcionalidades demonstradas

- Criação de pedidos com itens físicos e digitais
- Cálculo de descontos com estratégias variáveis
- Validação encadeada de pedidos
- Processamento com template para diferentes tipos de produto
- Adição opcional de serviços (embrulho, seguro, entrega expressa)
- Máquina de estados com transições válidas e inválidas
- Gerenciamento centralizado de inventário
- Pagamentos via Cartão de Crédito, PIX ou Boleto
