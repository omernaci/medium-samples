package com.omernaci.payment.factory;

public class BankTransferPaymentProcessor implements PaymentProcessor {

    @Override
    public void processPayment(String orderId) {
        System.out.println("Processing bank transfer payment for order ID: " + orderId);
    }

}
