package com.omernaci.payment.api;

import com.omernaci.payment.persistence.entity.PaymentStatus;
import com.omernaci.payment.service.PaymentService;
import com.omernaci.payment.service.dto.PaymentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("")
    public List<PaymentDto> getPaymentsByStatus(@RequestParam("paymentStatus") PaymentStatus paymentStatus) {
        return paymentService.findAllByPaymentStatus(paymentStatus);
    }
}