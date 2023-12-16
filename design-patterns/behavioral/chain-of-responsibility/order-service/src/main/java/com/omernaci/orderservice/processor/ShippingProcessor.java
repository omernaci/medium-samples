package com.omernaci.orderservice.processor;

import com.omernaci.orderservice.persistence.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class ShippingProcessor extends OrderProcessor {

    @Override
    public void process(Order order) {
        System.out.println("Shipping order: " + order.getOrderId() + " to address: " + order.getShippingAddress());

        if (nextProcessor != null) {
            nextProcessor.process(order);
        }
    }

}
