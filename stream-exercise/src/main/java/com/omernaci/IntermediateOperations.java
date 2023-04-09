package com.omernaci;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class IntermediateOperations {

    public static void main(String[] args) {

        List<Payment> payments = Arrays.asList(
                new Payment("1", "John", new BigDecimal("100.50"), Payment.PaymentStatus.APPROVED, LocalDate.now()),
                new Payment("2", "Mary", new BigDecimal("200.75"), Payment.PaymentStatus.PENDING, LocalDate.now().minusDays(60)),
                new Payment("3", "John", new BigDecimal("50.00"), Payment.PaymentStatus.APPROVED, LocalDate.now()),
                new Payment("4", "Bob", new BigDecimal("150.25"), Payment.PaymentStatus.REJECTED, LocalDate.now()),
                new Payment("5", "Mary", new BigDecimal("75.00"), Payment.PaymentStatus.PENDING, LocalDate.now().minusDays(15))
        );

        // filter() to get only the approved payments:
        List<Payment> approvedPayments = payments.stream()
                .filter(p -> p.getStatus() == Payment.PaymentStatus.APPROVED)
                .peek(System.out::println)
                .toList();

        // filter() to get only the approved payments made by John:
        List<Payment> approvedPaymentsByJohn = payments.stream()
                .filter(p -> p.getStatus() == Payment.PaymentStatus.APPROVED && p.getCustomerName().equals("John"))
                .toList();

        // map() to transform the Payment objects into their amounts:
        List<BigDecimal> paymentAmounts = payments.stream()
                .map(Payment::getAmount)
                .toList();

        // sorted() to sort the payments by their amount in descending order:
        List<Payment> sortedPayments = payments.stream()
                .sorted(Comparator.comparing(Payment::getAmount))
                .toList();

        // distinct() to get the unique customer names:
        List<String> uniqueCustomerNames = payments.stream()
                .map(Payment::getCustomerName)
                .distinct()
                .toList();

        // limit() to get the first two payments made by Mary:
        List<Payment> firstTwoPaymentsByMary = payments.stream()
                .filter(p -> p.getCustomerName().equals("Mary"))
                .limit(2)
                .toList();

    }

}