package com.omernaci.payment.service;

import com.omernaci.payment.persistence.entity.PaymentStatus;
import com.omernaci.payment.service.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    List<PaymentDto> findAllByPaymentStatus(PaymentStatus paymentStatus);

}
