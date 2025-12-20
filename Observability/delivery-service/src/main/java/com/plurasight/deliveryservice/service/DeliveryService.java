package com.plurasight.deliveryservice.service;

import com.plurasight.deliveryservice.model.Delivery;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {


    public Delivery createDelivery(Delivery delivery) {
        return generateDeliveryId(delivery);
    }

    private Delivery generateDeliveryId(Delivery delivery) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').get();
        String randomId = generator.generate(10);
        delivery.setId(randomId);
        return delivery;
    }
}
