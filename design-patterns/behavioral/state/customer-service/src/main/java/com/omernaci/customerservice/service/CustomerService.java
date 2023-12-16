package com.omernaci.customerservice.service;

import com.omernaci.customerservice.persistence.entity.Customer;

public interface CustomerService {

	Customer createCustomer(String name, String email);

	void deleteCustomer(Long customerId);

}
