package com.omernaci.notificationservice.service;

import com.omernaci.notificationservice.service.dto.CustomerDto;

public interface NotificationService {

	void sendAttendeeRegisterNotification(CustomerDto customerDto);

}
