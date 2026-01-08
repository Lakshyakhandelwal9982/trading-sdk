package com.bajajbroking.tradingsdk.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bajajbroking.tradingsdk.model.Instrument;
import com.bajajbroking.tradingsdk.model.Order;
import com.bajajbroking.tradingsdk.model.OrderStatus;
import com.bajajbroking.tradingsdk.model.OrderStyle;
import com.bajajbroking.tradingsdk.model.OrderType;
import com.bajajbroking.tradingsdk.model.Trade;
import com.bajajbroking.tradingsdk.repository.InstrumentRepository;
import com.bajajbroking.tradingsdk.repository.OrderRepository;
import com.bajajbroking.tradingsdk.repository.TradeRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InstrumentRepository instrumentRepository;
    private final TradeRepository tradeRepository;

    public OrderService(OrderRepository orderRepository,
                        InstrumentRepository instrumentRepository,
                        TradeRepository tradeRepository) {
        this.orderRepository = orderRepository;
        this.instrumentRepository = instrumentRepository;
        this.tradeRepository = tradeRepository;
    }

    // ===============================
    // Place a new order
    // ===============================
    public Order placeOrder(Order order) {

        // 1️⃣ System-generated fields
        order.setOrderId(UUID.randomUUID().toString());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.NEW);

        // 2️⃣ Validations
        if (order.getQuantity() == null || order.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        if (order.getOrderStyle() == OrderStyle.LIMIT && order.getPrice() == null) {
            throw new IllegalArgumentException("Price is mandatory for LIMIT orders");
        }

        // 3️⃣ Validate instrument
        Instrument instrument = instrumentRepository.getAllInstruments()
                .stream()
                .filter(i -> i.getSymbol().equalsIgnoreCase(order.getSymbol()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid instrument symbol"));

        // 4️⃣ Execution logic
        boolean executed = false;

        if (order.getOrderStyle() == OrderStyle.MARKET) {
            order.setPrice(instrument.getLastTradedPrice());
            order.setStatus(OrderStatus.EXECUTED);
            executed = true;
        } else {
            if (order.getOrderType() == OrderType.BUY &&
                    order.getPrice() >= instrument.getLastTradedPrice()) {
                order.setStatus(OrderStatus.EXECUTED);
                executed = true;
            } else if (order.getOrderType() == OrderType.SELL &&
                    order.getPrice() <= instrument.getLastTradedPrice()) {
                order.setStatus(OrderStatus.EXECUTED);
                executed = true;
            } else {
                order.setStatus(OrderStatus.PLACED);
            }
        }

        // 5️⃣ Save order
        Order savedOrder = orderRepository.save(order);

        // 6️⃣ Create trade if executed
        if (executed) {
            Trade trade = new Trade(savedOrder);
            tradeRepository.save(trade);
        }

        return savedOrder;
    }

    // ===============================
    // Fetch order by ID
    // ===============================
    public Optional<Order> getOrderById(String orderId) {
        return orderRepository.findById(orderId);
    }
}
