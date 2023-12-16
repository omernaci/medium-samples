package com.omernaci.customerservice.state;

public interface State<T> {

	void performAction(T context);

}
