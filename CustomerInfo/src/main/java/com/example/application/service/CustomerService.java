package com.example.application.service;

import java.util.List;

import com.example.application.model.Customer;

public interface CustomerService {

	List<Customer> getCustomers();
	Customer addCustomer(Customer customer);
	void deleteCustomer(Long customerId);
	
}
