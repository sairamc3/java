package com.plurasight.deliveryservice.controller;

import com.plurasight.deliveryservice.model.Delivery;
import com.plurasight.deliveryservice.service.DeliveryService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Timed(value = "latencyInSec.deliveries")
    @Counted(value = "counter.deliveries")
    @PostMapping("/deliveries")
    public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery){
        return ResponseEntity.ok(deliveryService.createDelivery(delivery));
    }
}
