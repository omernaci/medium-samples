package com.omernaci.executorexample;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final NotificationService notificationService;

    public OrderController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/place")
    public String placeOrder(@RequestParam String email, @RequestParam String phone) {
        String orderId = "ORD-" + System.currentTimeMillis();
        notificationService.sendNotification(orderId, email, phone);
        return "Order placed: " + orderId;
    }
}
