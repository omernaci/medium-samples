package com.omernaci.notificationservice.service.impl;

import com.omernaci.notificationservice.service.NotificationService;
import com.omernaci.notificationservice.service.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@RabbitListener(queues = {"${customer.queue.name}"})
	@Override
	public void sendAttendeeRegisterNotification(CustomerDto customerDto) {
		LOGGER.info(String.format("Received message -> %s", customerDto));
	}

}
