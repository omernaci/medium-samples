package com.omernaci.orderservice.config;

import com.omernaci.orderservice.processor.NotificationProcessor;
import com.omernaci.orderservice.processor.PaymentProcessor;
import com.omernaci.orderservice.processor.ShippingProcessor;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderProcessingConfiguration {

    private final PaymentProcessor paymentProcessor;
    private final ShippingProcessor shippingProcessor;
    private final NotificationProcessor notificationProcessor;

    @Autowired
    public OrderProcessingConfiguration(PaymentProcessor paymentProcessor,
        ShippingProcessor shippingProcessor,
        NotificationProcessor notificationProcessor) {
        this.paymentProcessor = paymentProcessor;
        this.shippingProcessor = shippingProcessor;
        this.notificationProcessor = notificationProcessor;
    }

    @PostConstruct
    public void configureOrderProcessingChain() {
        paymentProcessor.setNext(shippingProcessor);
        shippingProcessor.setNext(notificationProcessor);
    }

}
