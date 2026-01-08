package com.bajajbroking.tradingsdk.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bajajbroking.tradingsdk.model.Order;
import com.bajajbroking.tradingsdk.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Place a new order
    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    // Fetch order status by orderId
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable String orderId) {
        Optional<Order> orderOpt = orderService.getOrderById(orderId);

        if (orderOpt.isEmpty()) {
            throw new RuntimeException("Order not found");
        }

        return orderOpt.get();
    }
}
