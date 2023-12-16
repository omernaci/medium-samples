package com.omernaci.customerservice.state;

import com.omernaci.customerservice.persistence.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class ActiveState implements State<Customer> {

	@Override
	public void performAction(Customer context) {
		sendWelcomeEmail(context);
		activateServices(context);
	}

	private void sendWelcomeEmail(Customer customer) {
		System.out.println("send welcome email");
	}

	private void activateServices(Customer customer) {
		System.out.println("activate services");
	}

}
