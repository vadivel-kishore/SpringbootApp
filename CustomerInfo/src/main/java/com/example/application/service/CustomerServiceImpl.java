package com.example.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.model.Customer;
import com.example.application.repository.CustomerRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CustomerServiceImpl implements CustomerService{

	private final CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository=customerRepository;
	}

	
	@Override
	public List<Customer> getCustomers() {
		List<Customer> customerList = customerRepository.findAll();
		log.debug("Total number of customers retrieved from backend : "+customerList.size());
		return customerList;
	}

	@Override
	public Customer addCustomer(Customer customer) {
		Customer data=customerRepository.save(customer);
		log.debug("Customer saved to backend successfully with First Name : "+data.getFirstName());
		return data;
		
	}

	@Override
	public void deleteCustomer(Long customerId) {
		customerRepository.deleteById(customerId);		
		log.warn("customer with ID "+customerId+" deleted successfully from backend");
	}

}
