package com.example.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.application.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByEmailID(String emailID);

	public List<Customer> findByFirstName(String firstName);
}
