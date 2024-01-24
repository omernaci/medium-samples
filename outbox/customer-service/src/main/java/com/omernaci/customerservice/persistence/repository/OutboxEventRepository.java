package com.omernaci.customerservice.persistence.repository;

import com.omernaci.customerservice.persistence.entity.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxEventRepository extends JpaRepository<OutboxEvent, Long> {
}
