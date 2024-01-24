package com.omernaci.customerservice.api;

import com.omernaci.customerservice.service.CustomerService;
import com.omernaci.customerservice.service.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("")
	public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
		return ResponseEntity.ok(customerService.createCustomer(customerDto));
	}

}
