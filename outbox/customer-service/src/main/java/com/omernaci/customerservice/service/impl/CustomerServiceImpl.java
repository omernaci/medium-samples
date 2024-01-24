package com.omernaci.customerservice.service.impl;

import com.google.gson.Gson;
import com.omernaci.customerservice.exception.CustomerRegistrationException;
import com.omernaci.customerservice.persistence.entity.Customer;
import com.omernaci.customerservice.persistence.entity.EventStatus;
import com.omernaci.customerservice.persistence.entity.OutboxEvent;
import com.omernaci.customerservice.persistence.repository.CustomerRepository;
import com.omernaci.customerservice.persistence.repository.OutboxEventRepository;
import com.omernaci.customerservice.service.CustomerProducerService;
import com.omernaci.customerservice.service.CustomerService;
import com.omernaci.customerservice.service.dto.CustomerDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerProducerService customerProducerService;
	private final OutboxEventRepository outboxEventRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository,
							   CustomerProducerService customerProducerService,
							   OutboxEventRepository outboxEventRepository) {
		this.customerRepository = customerRepository;
		this.customerProducerService = customerProducerService;
		this.outboxEventRepository = outboxEventRepository;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		Optional<Customer> customerOptional = customerRepository.findByEmail(customerDto.email());

		if (customerOptional.isPresent()) {
			throw new CustomerRegistrationException("Username or email already in use.");
		}

		Customer savedCustomer = customerRepository.save(new Customer(customerDto.username(), customerDto.email()));

		//customerProducerService.sendCustomerCreatedEvent(savedCustomer);

		OutboxEvent outboxEvent = new OutboxEvent();
		outboxEvent.setEventPayload(new Gson().toJson(savedCustomer));
		outboxEvent.setEventType("CUSTOMER_CREATED");
		outboxEvent.setEventStatus(EventStatus.PENDING);
		outboxEvent.setCreateDate(LocalDateTime.now());

		outboxEventRepository.save(outboxEvent);

		return new CustomerDto(savedCustomer.getUsername(), savedCustomer.getEmail());
	}

}
