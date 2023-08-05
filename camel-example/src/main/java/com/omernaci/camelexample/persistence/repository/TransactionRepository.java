package com.omernaci.camelexample.persistence.repository;

import com.omernaci.camelexample.persistence.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
