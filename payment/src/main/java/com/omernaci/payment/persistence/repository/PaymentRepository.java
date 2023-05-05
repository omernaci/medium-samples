package com.omernaci.payment.persistence.repository;

import com.omernaci.payment.persistence.entity.Payment;
import com.omernaci.payment.persistence.entity.PaymentProjection;
import com.omernaci.payment.persistence.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "SELECT p.reference_number, p.amount, p.created_date, sa.account_number AS source_account_number, da.account_number AS destination_account_number, p.payment_status " +
            "FROM payment p " +
            "JOIN account sa ON p.source_account_id = sa.id " +
            "JOIN account da ON p.destination_account_id = da.id " +
            "WHERE p.payment_status = ?1", nativeQuery = true)
    List<PaymentProjection> findByPaymentStatus(PaymentStatus paymentStatus);

}
