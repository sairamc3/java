package com.plurasight.deliveryservice.service;

import com.plurasight.deliveryservice.model.Delivery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeliveryService {


    public Delivery createDelivery(Delivery delivery) {

        log.info("Creating delivery for source {}, and destination {}", delivery.getSource(), delivery.getDestination());

        Delivery returnedDelivery = generateDeliveryId(delivery);

        log.info("Order delivery created with id {}, source {}, destination {}",
                returnedDelivery.getId(),
                delivery.getSource(),
                delivery.getDestination());

        return returnedDelivery;
    }

    private Delivery generateDeliveryId(Delivery delivery) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').get();
        String randomId = generator.generate(10);
        delivery.setId(randomId);
        return delivery;
    }
}
