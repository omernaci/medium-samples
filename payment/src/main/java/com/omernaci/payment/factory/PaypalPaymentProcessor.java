package com.omernaci.payment.factory;

public class PaypalPaymentProcessor implements PaymentProcessor {

    @Override
    public void processPayment(String orderId) {
        System.out.println("Processing paypal payment for order ID: " + orderId);
    }

}
