package com.omernaci.customerservice.persistence.entity;

import com.omernaci.customerservice.state.ActiveState;
import com.omernaci.customerservice.state.State;
import jakarta.persistence.*;

@Table
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String email;
	@Transient
	private State<Customer> state;

	public Customer() {
		this.state = new ActiveState();
	}

	public void setState(State<Customer> state) {
		this.state = state;
	}

	public void performAction() {
		state.performAction(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
