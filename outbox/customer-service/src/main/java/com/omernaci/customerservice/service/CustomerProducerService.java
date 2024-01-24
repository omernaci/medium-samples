package com.omernaci.customerservice.service;

import com.omernaci.customerservice.service.dto.CustomerDto;

public interface CustomerProducerService {

	void sendCustomerCreatedEvent(CustomerDto savedCustomer);

}
