package com.example.application.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.application.model.Customer;
import com.example.application.service.CustomerServiceImpl;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ExtendWith(MockitoExtension.class)
class CustomerRepositoryTest {

	@Mock
	private CustomerRepository customerRepository;

	private CustomerServiceImpl customerService;

	@BeforeEach
	void setup() {
		customerService = new CustomerServiceImpl(customerRepository);
	}

	private Customer testCustomer() {
		return Customer.builder().firstName("Kishore").lastName("Vadivel").emailID("kishoretest@gmail.com").build();
	}

	/*
	 * Test case to validate the functionality of add Customer(POST) based on "save"
	 * method.
	 */
	@Test
	public void testAddCustomer() {
		Customer customer = this.testCustomer();

		when(customerRepository.save(isA(Customer.class))).thenAnswer(c -> (Customer) c.getArguments()[0]);
		customerService.addCustomer(customer);

		ArgumentCaptor<Customer> savedCustomerInMemory = ArgumentCaptor.forClass(Customer.class);
		verify(customerRepository).save(savedCustomerInMemory.capture());

		Customer savedcustomer = savedCustomerInMemory.getValue();
		assertThat(savedcustomer).isEqualTo(customer);
	}

	/*
	 * Test case to validate the functionality of GET all Customers based on
	 * "findAll" method.
	 */
	@Test
	public void testFindAll() {
		Customer customer = this.testCustomer();

		when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));
		// customerService.addCustomer(customer);

		List<Customer> customerList = customerService.getCustomers();
		assertThat(customerList).hasSize(1);
	}

	/*
	 * Test case to validate the delete functionality of Customer object based on
	 * "findById" method.
	 */
	@Test
	public void testDeleteById() {
		Long test_ID = 25L;
		Optional<Customer> customer = Optional.of(Customer.builder().id(test_ID).firstName("Kishore")
				.lastName("Vadivel").emailID("kishoretest@gmail.com").build());

		lenient().when(customerRepository.findById(test_ID)).thenReturn(customer);
		customerService.deleteCustomerByID(test_ID);

		verify(customerRepository).deleteById(test_ID);
	}

	/*
	 * Test case to validate the Customer object based on "findByEmailID" method.
	 */
	@Test
	void testFindByEmailID() {
		String test_emailID = "kishoretest@gmail.com";
		Customer customer = this.testCustomer();

		lenient().when(customerRepository.findByEmailID(test_emailID)).thenReturn(customer);
		Customer customerResult = customerService.getCustomerByEmailID(test_emailID);
		assertThat(customerResult).isNotNull();
	}

	/*
	 * Test case to validate the count on retrieved customer List based on
	 * "findByFirstName" method.
	 */
	@Test
	void testFindByFirstName() {
		String test_firstName = "kishore";
		Customer customer1 = Customer.builder().id(1L).firstName(test_firstName).emailID("test@test.com").build();
		Customer customer2 = Customer.builder().id(2L).firstName("Satish").emailID("test2@test.com").build();
		Customer customer3 = Customer.builder().id(3L).firstName(test_firstName).emailID("test3@test.com").build();

		lenient().when(customerRepository.findByFirstName(test_firstName))
				.thenReturn(List.of(customer1, customer2, customer3).stream()
						.filter(customers -> customers.getFirstName().contains(test_firstName))
						.collect(Collectors.toList()));

		List<Customer> customerResult = customerService.getCustomerByFirstName(test_firstName);
		log.debug(customerResult.size());

		assertThat(customerResult).isNotNull();
		assertThat(customerResult).hasSize(2);
	}
}
