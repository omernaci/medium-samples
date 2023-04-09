package com.omernaci;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Payment {
    private String id;
    private String customerName;
    private BigDecimal amount;
    private PaymentStatus status;
    private LocalDate date;

    public Payment(String id, String customerName, BigDecimal amount, PaymentStatus status, LocalDate date) {
        this.id = id;
        this.customerName = customerName;
        this.amount = amount;
        this.status = status;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", customerName='" + customerName + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                ", date=" + date +
                '}';
    }

    public boolean isApproved() {
        return status == PaymentStatus.APPROVED;
    }

    public boolean isOverdue() {
        return status == PaymentStatus.PENDING && LocalDate.now().isAfter(date.plusDays(30));
    }

    public void approve() {
        status = PaymentStatus.APPROVED;
    }

    public void reject() {
        status = PaymentStatus.REJECTED;
    }

    public void markAsPending() {
        status = PaymentStatus.PENDING;
    }

    public enum PaymentStatus {
        PENDING,
        APPROVED,
        REJECTED
    }
}
