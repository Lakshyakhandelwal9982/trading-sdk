package com.bajajbroking.tradingsdk.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bajajbroking.tradingsdk.model.Order;

@Repository
public class OrderRepository {

    // In-memory storage for orders
    private final Map<String, Order> orderStore = new HashMap<>();

    // Save or update order
    public Order save(Order order) {
        orderStore.put(order.getOrderId(), order);
        return order;
    }

    // Find order by orderId
    public Optional<Order> findById(String orderId) {
        return Optional.ofNullable(orderStore.get(orderId));
    }

    // Get all orders
    public List<Order> findAll() {
        return new ArrayList<>(orderStore.values());
    }
}
