package com.omernaci.customerservice.service.impl;

import com.omernaci.customerservice.service.CustomerProducerService;
import com.omernaci.customerservice.service.dto.CustomerDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerProducerServiceImpl implements CustomerProducerService {

	private final RabbitTemplate rabbitTemplate;

	public CustomerProducerServiceImpl(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void sendCustomerCreatedEvent(CustomerDto savedCustomer) {
		rabbitTemplate.convertAndSend("customerExchange", "customerRoutingKey", savedCustomer);
	}

}
