package com.omernaci.payment.factory;

public class CreditCardPaymentProcessor implements PaymentProcessor {

    @Override
    public void processPayment(String orderId) {
        System.out.println("Processing credit card payment for order ID: " + orderId);
    }

}
