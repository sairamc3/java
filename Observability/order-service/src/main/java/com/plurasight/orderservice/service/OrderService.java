package com.plurasight.orderservice.service;

import com.plurasight.orderservice.exception.OrderNotFoundException;
import com.plurasight.orderservice.externalcalls.DeliveryClient;
import com.plurasight.orderservice.model.Order;
import com.plurasight.orderservice.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryClient deliveryClient;

    private static final String CREATED = "CREATED";
    private static final String IN_DELIVERY = "IN_DELIVERY";

    public Order createOrder(Order order) {

        log.info("Trying to persist the order with details {}", order);

        order.setOrderStatus(CREATED);
        orderRepository.save(order);

        log.info("Successfully persisted the order with details {}", order);

        deliveryClient.createDeliveryForOrder(order);
        order.setOrderStatus(IN_DELIVERY);
        return order;

    }

    public Order findOrder(String orderId) {
        log.info("Trying to find the order with id {}", orderId);
        return orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    log.error("Failed to find the order with id {}", orderId);
                    return new OrderNotFoundException("Order with Id " + orderId + " not found");
                });
    }


}
