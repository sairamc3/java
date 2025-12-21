package com.plurasight.orderservice.externalcalls;

import com.plurasight.orderservice.exception.DeliveryCreationException;
import com.plurasight.orderservice.model.Delivery;
import com.plurasight.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryClient {

    private final RestClient restClient;
    private static final String DELIVERY_URL = "/deliveries";
    private static final String BASE_URL = "http://localhost:6001";

    public Delivery createDeliveryForOrder(Order order) {

        log.info("Trying to create delivery for order {}", order);

        Delivery deliveryFromOrder = getDeliveryFromOrder(order);

        Delivery delivery = null;
        try {
            delivery = this.restClient.post().uri(BASE_URL + DELIVERY_URL)
                    .body(deliveryFromOrder)
                    .retrieve()
                    .body(Delivery.class);
            log.info("Successfully created delivery for order {}", order);
        } catch (Exception e) {
            log.error("Failed to create delivery for order {}", order, e);
            throw new DeliveryCreationException("Failed to create delivery for the order " + order.getOrderId());
        }
        return delivery;
    }

    private Delivery getDeliveryFromOrder(Order order) {
        return Delivery.builder()
                .source(order.getSource())
                .destination(order.getDestination())
                .build();
    }

}
