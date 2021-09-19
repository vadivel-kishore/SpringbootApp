package com.example.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.application.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
