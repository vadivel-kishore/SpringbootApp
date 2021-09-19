package com.example.application.controller;

import java.util.List;

import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.model.Customer;
import com.example.application.service.CustomerService;

@RestController
@RequestMapping(path="api/", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

	
	private final CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService=customerService;
	}
	
	@GetMapping(value="/customer")
	public List<Customer> loadCustomers()
	{
		return customerService.getCustomers();
	}

	@PostMapping(value="/customer")
	public Customer addCustomer(@RequestBody Customer customer)
	{
		return customerService.addCustomer(customer);
	}
	
	@DeleteMapping(value="/customer/{customerID}")
	public String addCustomer(@PathVariable("customerID") Long customerID)
	{
		customerService.deleteCustomer(customerID);
		return "Customer with ID "+customerID+" deleted successfully";
	}

	
}
