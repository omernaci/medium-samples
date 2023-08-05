package com.omernaci.camelexample.service;

import com.omernaci.camelexample.persistence.entity.Transaction;

public interface TransactionService {

    void logSuspiciousActivity(Transaction transaction);

}
