package com.omernaci.camelexample.service.impl;

import com.omernaci.camelexample.persistence.entity.SuspiciousActivity;
import com.omernaci.camelexample.persistence.entity.Transaction;
import com.omernaci.camelexample.persistence.repository.SuspiciousActivityRepository;
import com.omernaci.camelexample.service.TransactionService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final SuspiciousActivityRepository suspiciousActivityRepository;

    public TransactionServiceImpl(SuspiciousActivityRepository suspiciousActivityRepository) {
        this.suspiciousActivityRepository = suspiciousActivityRepository;
    }

    @Override
    public void logSuspiciousActivity(Transaction transaction) {

        System.out.println("Suspicious Activity Detected: " + transaction.getAccountNumber());
        SuspiciousActivity suspiciousActivity = new SuspiciousActivity();
        suspiciousActivity.setAccountNumber(transaction.getAccountNumber());
        suspiciousActivity.setAmount(transaction.getAmount());
        suspiciousActivity.setSourceLocation(transaction.getSourceLocation());
        suspiciousActivity.setLogDate(LocalDateTime.now());

        suspiciousActivityRepository.save(suspiciousActivity);
    }

}
