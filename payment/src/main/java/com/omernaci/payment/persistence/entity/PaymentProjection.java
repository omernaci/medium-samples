package com.omernaci.payment.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PaymentProjection {

    String getReferenceNumber();

    BigDecimal getAmount();

    LocalDateTime getCreatedDate();

    String getSourceAccountNumber();

    String getDestinationAccountNumber();

    PaymentStatus getPaymentStatus();

}
