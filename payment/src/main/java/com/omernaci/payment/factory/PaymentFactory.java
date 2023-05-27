package com.omernaci.payment.factory;

import org.springframework.stereotype.Component;

@Component
public class PaymentFactory {

    public static PaymentProcessor createPaymentProcessor(PaymentType paymentType) {
        return switch (paymentType) {
            case CREDIT_CARD -> new CreditCardPaymentProcessor();
            case PAYPAL -> new PaypalPaymentProcessor();
            case BANK_TRANSFER -> new BankTransferPaymentProcessor();
        };
    }

}
