package com.omernaci.payment.service.impl;

import com.omernaci.payment.factory.PaymentFactory;
import com.omernaci.payment.factory.PaymentProcessor;
import com.omernaci.payment.factory.PaymentType;
import com.omernaci.payment.persistence.repository.PaymentRepository;
import com.omernaci.payment.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PaymentProcessor paymentProcessor;

    @Mock
    private PaymentRepository paymentRepository;

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentService = new PaymentServiceImpl(paymentRepository);
    }

    @Test
    void processPayment() {
        String orderId = "123";
        PaymentType paymentType = PaymentType.CREDIT_CARD;

        try (MockedStatic<PaymentFactory> mockedFactory = Mockito.mockStatic(PaymentFactory.class)) {
            mockedFactory.when(() -> PaymentFactory.createPaymentProcessor(paymentType)).thenReturn(paymentProcessor);

            paymentService.processPayment(orderId, paymentType);

            mockedFactory.verify(() -> PaymentFactory.createPaymentProcessor(paymentType));

            verify(paymentProcessor).processPayment(orderId);
        }
    }
}