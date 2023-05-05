package com.omernaci.payment.service.impl;

import com.omernaci.payment.persistence.entity.PaymentProjection;
import com.omernaci.payment.persistence.entity.PaymentStatus;
import com.omernaci.payment.persistence.repository.PaymentRepository;
import com.omernaci.payment.service.PaymentService;
import com.omernaci.payment.service.dto.PaymentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<PaymentDto> findAllByPaymentStatus(PaymentStatus paymentStatus) {

        List<PaymentProjection> paymentProjectionList = paymentRepository.findByPaymentStatus(paymentStatus);

        return paymentProjectionList.stream()
                .map(paymentProjection -> new PaymentDto(
                        paymentProjection.getReferenceNumber(),
                        paymentProjection.getAmount(),
                        paymentProjection.getCreatedDate(),
                        paymentProjection.getSourceAccountNumber(),
                        paymentProjection.getDestinationAccountNumber(),
                        paymentProjection.getPaymentStatus()))
                .collect(Collectors.toList());
    }
}
