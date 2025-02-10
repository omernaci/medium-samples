package com.omernaci.cdcquerybasedexample;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderCDCService {

    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;
    private LocalDateTime lastPollTime = LocalDateTime.now().minusMinutes(5);

    public OrderCDCService(OrderRepository orderRepository, RabbitTemplate rabbitTemplate) {
        this.orderRepository = orderRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 30000)
    public void checkForChanges() {
        List<Order> changedOrders = orderRepository.findByLastUpdatedAfter(lastPollTime);

        if (!changedOrders.isEmpty()) {

            for (Order order : changedOrders) {
                rabbitTemplate.convertAndSend("order-changes-queue", order);
            }

            lastPollTime = LocalDateTime.now();
        }

    }

}
