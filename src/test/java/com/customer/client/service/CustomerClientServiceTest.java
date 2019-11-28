package com.customer.client.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.customer.client.model.Customer;

@SpringBootTest
public class CustomerClientServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private CustomerClientService service;

	@Test
	public void getCustomerByEmailTest() {
		final String GET_CUSTOMER_BY_EMAIL = "http://localhost:6060/getEmail/{email}";

		Customer customer = new Customer();
		customer.setCustomerId(101);
		customer.setCustomerEmail("nreddy123@gmail.com");
		customer.setCustomerName("nreddy");
		ResponseEntity<Customer> entity = restTemplate.getForEntity(GET_CUSTOMER_BY_EMAIL, Customer.class,
				customer.getCustomerEmail());
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(customer, HttpStatus.OK);
		when(entity).thenReturn(responseEntity);

		Customer actual = service.getCustomerByEmail("nreddy123@gmail.com");
		assertEquals(customer, actual);

	}

	@Test
	public void getCustomerByIdTest() {
		final String GET_CUSTOMER_BY_ID = "http://localhost:6060/get?customerID=";
		Customer customer = new Customer();
		customer.setCustomerId(101);
		customer.setCustomerEmail("nreddy123@gmail.com");
		customer.setCustomerName("nreddy");
		ResponseEntity<Customer> entity = restTemplate.getForEntity(GET_CUSTOMER_BY_ID + customer.getCustomerId(),
				Customer.class);
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(customer, HttpStatus.OK);
		when(entity).thenReturn(responseEntity);
		Customer actual = service.getCustomerById(101);
		assertEquals(customer, actual);

	}

	@Test
	public void getAllCustomerEmailsTest() {

		String emails = "nreddy@gmail.com";
		final String GET_ALL_CUSTOMER_EMAILS = "http://localhost:6060/getEmails";
		ResponseEntity<String> entity = restTemplate.getForEntity(GET_ALL_CUSTOMER_EMAILS, String.class);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(emails, HttpStatus.OK);
		when(entity).thenReturn(responseEntity);
		String actual = service.getAllCustomerEmails();
		assertEquals(emails, actual);

	}

	@Test
	public void addCustomerDetailsTest() {
		final String ADD_CUSTOMER_DETAILS = "http://localhost:6060/add";
		//String added = "Customer details sucessfully added";
		Customer customer = new Customer();
		customer.setCustomerId(102);
		customer.setCustomerEmail("test123@gmail.com");
		customer.setCustomerName("test");

		HttpHeaders header = new HttpHeaders();
		header.add("content-type", "application/json");
		// header.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Customer> httpEntity = new HttpEntity<>(customer, header);

		ResponseEntity<String> entity = restTemplate.postForEntity(ADD_CUSTOMER_DETAILS, httpEntity, String.class);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.CREATED);
		when(entity).thenReturn(responseEntity);
		ResponseEntity<String> actual = service.addCustomerDetails(customer);
		assertEquals(201, actual.getStatusCodeValue());

	}
}