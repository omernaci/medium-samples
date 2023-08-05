package com.omernaci.camelexample.persistence.repository;

import com.omernaci.camelexample.persistence.entity.SuspiciousActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuspiciousActivityRepository extends JpaRepository<SuspiciousActivity, Long> {
}
