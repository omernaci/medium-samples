package com.omernaci.customerservice.api;

import com.omernaci.customerservice.persistence.entity.Customer;
import com.omernaci.customerservice.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestParam String name, @RequestParam String email) {
		Customer customer = customerService.createCustomer(name, email);
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
		customerService.deleteCustomer(customerId);
		return new ResponseEntity<>("Customer deleted", HttpStatus.OK);
	}

}