package com.omernaci.customerservice.service.impl;

import com.omernaci.customerservice.persistence.entity.Customer;
import com.omernaci.customerservice.persistence.repository.CustomerRepository;
import com.omernaci.customerservice.service.CustomerService;
import com.omernaci.customerservice.state.ActiveState;
import com.omernaci.customerservice.state.DeletedState;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer createCustomer(String name, String email) {
		Customer customer = new Customer();
		customer.setName(name);
		customer.setEmail(email);
		customer.setState(new ActiveState());
		customer.performAction();
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public void deleteCustomer(Long customerId) {
		Customer customer = getCustomerById(customerId);
		if (customer != null) {
			customer.setState(new DeletedState());
			customer.performAction();
			customerRepository.delete(customer);
		}
	}

	public Customer getCustomerById(Long customerId) {
		return customerRepository.findById(customerId).orElse(null);
	}

}
