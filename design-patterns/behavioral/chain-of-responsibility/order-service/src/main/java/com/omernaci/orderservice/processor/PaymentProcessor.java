package com.omernaci.orderservice.processor;

import com.omernaci.orderservice.persistence.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor extends OrderProcessor {

    @Override
    public void process(Order order) {
        System.out.println("Processing payment for order: " + order.getOrderId());

        if (nextProcessor != null) {
            nextProcessor.process(order);
        }
    }

}
