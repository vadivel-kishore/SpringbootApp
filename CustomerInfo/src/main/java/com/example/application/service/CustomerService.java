package com.example.application.service;

import java.util.List;

import com.example.application.model.Customer;

public interface CustomerService {

	List<Customer> getCustomers();

	List<Customer> getCustomerByFirstName(String firstName);

	Customer getCustomerByEmailID(String emailID);

	String addCustomer(Customer customer);

	String deleteCustomerByID(Long customerId);

}
