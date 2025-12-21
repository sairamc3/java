package com.plurasight.orderservice.controller;

import com.plurasight.orderservice.model.Order;
import com.plurasight.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {

        log.info("Creating order with details{}", order);

        Order createdOrder = this.orderService.createOrder(order);

        log.info("Successfully created order with details {}", createdOrder);

        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        log.info("Searching the order with id {}", orderId);
        Order order = this.orderService.findOrder(orderId);
        log.info("Successfully found order {} with id {}", order, orderId);
        return ResponseEntity.ok(order);
    }
}
