package com.plurasight.orderservice.service;

import com.plurasight.orderservice.exception.OrderNotFoundException;
import com.plurasight.orderservice.externalcalls.DeliveryClient;
import com.plurasight.orderservice.model.Order;
import com.plurasight.orderservice.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryClient deliveryClient;

    private static final String CREATED = "CREATED";
    private static final String IN_DELIVERY = "IN_DELIVERY";

    public Order createOrder(Order order) {

        order.setOrderStatus(CREATED);
        orderRepository.save(order);
        deliveryClient.createDeliveryForOrder(order);
        order.setOrderStatus(IN_DELIVERY);
        return order;

    }

    public Order findOrder(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with Id " + orderId + " not found"));
    }


}
