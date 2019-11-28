package com.customer.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.customer.client.model.Customer;

@Service
public class CustomerClientService {
	@Autowired
	private Environment env;


	@Autowired
	private RestTemplate restTemplate;
	
	


	public ResponseEntity<String> addCustomerDetails(Customer customer) {
		HttpHeaders header = new HttpHeaders();
		header.add("content-type", "application/json");
		// header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Customer> httpEntity = new HttpEntity<>(customer, header);

		ResponseEntity<String> entity = restTemplate.postForEntity(env.getProperty("ADD_CUSTOMER_DETAILS"), httpEntity, String.class);
		int status = entity.getStatusCode().value();

		if (status == 201) {
				String body = entity.getBody();
			return new ResponseEntity<String>(body,HttpStatus.CREATED);
		} else {
			return null;
		}

	}

	public Customer getCustomerByEmail(String email) {

		ResponseEntity<Customer> entity = restTemplate.getForEntity(env.getProperty("GET_CUSTOMER_BY_EMAIL"), Customer.class, email);
		int status = entity.getStatusCode().value();
		if (status == 200) {
			Customer body = entity.getBody();
			return body;
		} else {
			return null;
		}

	}

	public Customer getCustomerById(Integer customerID) {
		ResponseEntity<Customer> entity = restTemplate.getForEntity(env.getProperty("GET_CUSTOMER_BY_ID") + customerID, Customer.class);

		int status = entity.getStatusCode().value();

		if (status == 200) {
			Customer body = entity.getBody();
			return body;

		} else {
			return null;
		}

	}

	public String getAllCustomerEmails() {

		ResponseEntity<String> entity = restTemplate.getForEntity(env.getProperty("GET_ALL_CUSTOMER_EMAILS"), String.class);
		int status = entity.getStatusCode().value();
		if (status == 200) {
			String body = entity.getBody();
			return body;
		} else {
			return null;
		}

	}

}
