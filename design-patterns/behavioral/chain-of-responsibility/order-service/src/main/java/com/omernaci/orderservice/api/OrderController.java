package com.omernaci.orderservice.api;

import com.omernaci.orderservice.persistence.entity.Order;
import com.omernaci.orderservice.processor.PaymentProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final PaymentProcessor paymentProcessor;

    public OrderController(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    @PostMapping("/processOrder")
    public ResponseEntity<String> processOrder(@RequestBody Order order) {
        paymentProcessor.process(order);
        return ResponseEntity.ok("Order processed successfully.");
    }
}