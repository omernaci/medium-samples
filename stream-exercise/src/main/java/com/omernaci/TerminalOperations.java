package com.omernaci;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TerminalOperations {

    public static void main(String[] args) {

        List<Payment> payments = Arrays.asList(
                new Payment("1", "John", new BigDecimal("100.50"), Payment.PaymentStatus.APPROVED, LocalDate.now()),
                new Payment("2", "Mary", new BigDecimal("200.75"), Payment.PaymentStatus.PENDING, LocalDate.now().minusDays(60)),
                new Payment("3", "John", new BigDecimal("50.00"), Payment.PaymentStatus.APPROVED, LocalDate.now()),
                new Payment("4", "Bob", new BigDecimal("150.25"), Payment.PaymentStatus.REJECTED, LocalDate.now()),
                new Payment("5", "Mary", new BigDecimal("75.00"), Payment.PaymentStatus.PENDING, LocalDate.now().minusDays(15))
        );

        // count(): This terminal operation returns the number of elements in the stream.
        long count = payments.stream().count();
        System.out.println("Number of payments: " + count);
        // Output: Number of payments: 5

        // forEach(): This terminal operation performs an action for each element in the stream.
        payments.forEach(p -> System.out.println(p.getCustomerName() + ": " + p.getAmount()));
        /*
            Output:
                John: 100.50
                Mary: 200.75
                John: 50.00
                Bob: 150.25
                Mary: 75.00
         */

        // max(): This terminal operation returns the maximum element of the stream, according to a given comparator.
        Optional<Payment> maxPayment = payments.stream().max(Comparator.comparing(Payment::getAmount));
        maxPayment.ifPresent(payment -> System.out.println("Maximum payment amount: " + payment.getAmount()));
        // Output: Maximum payment amount: 200.75

        // min(): This terminal operation returns the minimum element of the stream, according to a given comparator.
        Optional<Payment> minPayment = payments.stream().min(Comparator.comparing(Payment::getAmount));
        minPayment.ifPresent(payment -> System.out.println("Minimum payment amount: " + payment.getAmount()));
        // Output: Minimum payment amount: 50.00

        // reduce(): This terminal operation reduces the elements of the stream to a single value, according to a given binary operator.
        Optional<BigDecimal> totalAmount = payments.stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal::add);
        totalAmount.ifPresent(bigDecimal -> System.out.println("Total amount: " + bigDecimal));
        // Output: Total amount: 576.50
    }

}
