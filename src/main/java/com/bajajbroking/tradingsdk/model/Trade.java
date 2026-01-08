package com.bajajbroking.tradingsdk.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trade {

    private String tradeId;
    private String orderId;
    private String symbol;
    private Integer quantity;
    private Double price;
    private LocalDateTime executedAt;

    public Trade(Order order) {
        this.tradeId = UUID.randomUUID().toString();
        this.orderId = order.getOrderId();
        this.symbol = order.getSymbol();
        this.quantity = order.getQuantity();
        this.price = order.getPrice();
        this.executedAt = LocalDateTime.now();
    }
}
