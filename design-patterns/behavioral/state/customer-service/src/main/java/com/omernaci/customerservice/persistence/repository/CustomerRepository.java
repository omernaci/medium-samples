package com.omernaci.customerservice.persistence.repository;

import com.omernaci.customerservice.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
