package com.example.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.model.Customer;
import com.example.application.repository.CustomerRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> getCustomers() {
		List<Customer> customerList = customerRepository.findAll();
		log.debug("Total number of customers retrieved from backend : " + customerList.size());
		return customerList;
	}

	@Override
	public String addCustomer(Customer customer) {

		Customer cutomerEmailExists = getCustomerByEmailID(customer.getEmailID());
		Optional<Customer> cutomerIdExists = customerRepository.findById(customer.getId());
		if(cutomerIdExists.isEmpty()) {
			if (cutomerEmailExists == null) {
				customerRepository.save(customer);
				return "Record Saved successfully";
			} else
				return "Customer with Email address : " + customer.getEmailID() + "  already exists in database";
		} else
			return "Customer with ID : " + customer.getId() + "  already exists in database";
	}

	@Override
	public String deleteCustomerByID(Long customerId) {
		
		Optional<Customer> customer = customerRepository.findById(customerId);
		if(!customer.isEmpty()) {			
			customerRepository.deleteById(customerId);
			log.warn("customer with ID " + customerId + " deleted successfully from backend");
			return ("customer with ID " + customerId + " deleted successfully from backend");
		}
		else {
			log.warn("No Customer found with ID " + customerId);
			return ("No Customer found with ID " + customerId);
		}		
	}

	@Override
	public List<Customer> getCustomerByFirstName(String firstName) {
		return customerRepository.findByFirstName(firstName);

	}

	@Override
	public Customer getCustomerByEmailID(String emailID) {
		return customerRepository.findByEmailID(emailID);
	}

}
