package com.omernaci.orderservice.processor;

import com.omernaci.orderservice.persistence.entity.Order;

public abstract class OrderProcessor {

    protected OrderProcessor nextProcessor;

    public void setNext(OrderProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    public abstract void process(Order order);

    public void processOrder(Order order) {
        System.out.println("Processing order: " + order.getOrderId());

        if (nextProcessor != null) {
            nextProcessor.process(order);
        }
    }

}
