package com.omernaci.customerservice.state;

import com.omernaci.customerservice.persistence.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class DeletedState implements State<Customer> {

	@Override
	public void performAction(Customer context) {
		closeAccounts(context);
		archiveData(context);
	}

	private void closeAccounts(Customer customer) {
		System.out.println("close accounts");
	}

	private void archiveData(Customer customer) {
		System.out.println("archive data");
	}

}
