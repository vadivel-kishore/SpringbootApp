package com.example.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.application.controller.CustomerController;
import com.example.application.model.Customer;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	@Mock
	private CustomerServiceImpl customerService;

	@InjectMocks
	private CustomerController controller;

	public List<Customer> loadTestData() {
		List<Customer> testData = List.of(
				Customer.builder().id(10L).firstName("Kishore").lastName("Vadivel").emailID("kishoretest@gmail.com1")
						.build(),
				Customer.builder().id(20L).firstName("Erik").lastName("Larsen").emailID("eriklars@gmail.com").build(),
				Customer.builder().id(30L).firstName("Anders").lastName("Øle").emailID("anders.øle@gmail.com").build(),
				Customer.builder().id(40L).firstName("henning").lastName("thomas").emailID("thomas-henningt@gmail.com")
						.build(),
				Customer.builder().id(50L).firstName("Henrik").lastName("Chinna").emailID("henrikchinna@gmail.com")
						.build());
		return testData;
	}

	/*
	 * Test case to validate the functionality of add Customer(POST) based on "save"
	 * method.
	 */
	@Test
	public void testAddCustomer() {
		List<Customer> customerList = this.loadTestData();
		String status = "Record Saved successfully";

		when(customerService.addCustomer(customerList.get(0))).thenReturn(status);
		// customerService.addCustomer(customer);

		String resultStatus = controller.addCustomer(customerList.get(0));
		assertThat(resultStatus).isEqualTo(status);
	}

	/*
	 * Test case to validate the functionality of GET all Customers based on
	 * "getCustomers" method.
	 */
	@Test
	public void testGetCustomers() {
		List<Customer> customerList = this.loadTestData();

		when(customerService.getCustomers()).thenReturn(customerList);
		// customerService.addCustomer(customer);

		List<Customer> customerResultList = controller.loadCustomers();
		assertThat(customerResultList).hasSize(5);
	}

	/*
	 * Test case to validate the delete functionality of Customer object based on
	 * "deleteCustomerByID" method.
	 */
	@Test
	public void testDeleteById() {
		Long test_ID = 20L;
		List<Customer> customerList = this.loadTestData();

		Optional<Customer> customer = customerList.stream().filter(c -> c.getId() == test_ID).findAny();

		String testData = "Success";
		if (customer.isEmpty())
			testData = "Fail";

		lenient().when(customerService.deleteCustomerByID(test_ID)).thenReturn("Success");
		String expectedResult = controller.deleteCustomerByID(test_ID);

		verify(customerService).deleteCustomerByID(test_ID);
		assertThat(expectedResult).isEqualTo(testData);
	}

	/*
	 * Test case to validate the Customer object based on "getCustomerByEmailID"
	 * method.
	 */
	@Test
	void testFindByEmailID() {
		String test_emailID = "kishoretest@gmail.com";
		Customer testData = Customer.builder().id(1L).firstName("Kishore").emailID(test_emailID).build();
		Customer expectedData = Customer.builder().id(1L).firstName("Kishore").emailID("kishoretest@gmail.com").build();

		lenient().when(customerService.getCustomerByEmailID(test_emailID)).thenReturn(testData);
		Customer customerResult = customerService.getCustomerByEmailID(test_emailID);

		assertThat(customerResult).isEqualTo(expectedData);
	}

	/*
	 * Test case to validate the count on retrieved customer List based on
	 * "getCustomerByFirstName" method.
	 */
	@Test
	void testFindByFirstName() {
		String test_firstName = "kishore";
		Customer customer1 = Customer.builder().id(1L).firstName(test_firstName).emailID("test@te2st.com").build();
		Customer customer2 = Customer.builder().id(2L).firstName("Satish").emailID("test2@test.com").build();
		Customer customer3 = Customer.builder().id(3L).firstName(test_firstName).emailID("test3@test.com").build();

		lenient().when(customerService.getCustomerByFirstName(test_firstName))
				.thenReturn(List.of(customer1, customer2, customer3).stream()
						.filter(customers -> customers.getFirstName().contains(test_firstName))
						.collect(Collectors.toList()));

		List<Customer> customerResult = controller.loadCustomerByFirstName(test_firstName);
		log.debug(customerResult.size());

		assertThat(customerResult).isNotNull();
		assertThat(customerResult).hasSize(2);
	}
}
