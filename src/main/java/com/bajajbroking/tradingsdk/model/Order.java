package com.bajajbroking.tradingsdk.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String orderId;
    private String symbol;
    private OrderType orderType;     // BUY or SELL
    private OrderStyle orderStyle;   // MARKET or LIMIT
    private Integer quantity;
    private Double price;            // required for LIMIT orders
    private OrderStatus status;
    private LocalDateTime createdAt;

    // Helper constructor to auto-generate orderId
    public Order(String symbol,
                 OrderType orderType,
                 OrderStyle orderStyle,
                 Integer quantity,
                 Double price) {

        this.orderId = UUID.randomUUID().toString();
        this.symbol = symbol;
        this.orderType = orderType;
        this.orderStyle = orderStyle;
        this.quantity = quantity;
        this.price = price;
        this.status = OrderStatus.NEW;
        this.createdAt = LocalDateTime.now();
    }
}
