package com.customer.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	Logger logger = LoggerFactory.getLogger(CustomerClientService.class);

	public CustomerClientService() {
		logger.debug("CustomerClientService::Instantiated");
		logger.info("CustomerClientService::Instantiated");
	}

	@Autowired
	private Environment env;

	@Autowired
	private RestTemplate restTemplate;

	public ResponseEntity<String> addCustomerDetails(Customer customer) {
		logger.debug("addCustomerDetails() method  is started");
		HttpHeaders header = new HttpHeaders();
		header.add("content-type", "application/json");
		// header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Customer> httpEntity = new HttpEntity<>(customer, header);

		ResponseEntity<String> entity = restTemplate.postForEntity(env.getProperty("ADD_CUSTOMER_DETAILS"), httpEntity,
				String.class);
		int status = entity.getStatusCode().value();

		if (status == 201) {
			String body = entity.getBody();
			logger.debug("addCustomerDetails() method  is ended");
			logger.info("addCustomerDetails() method  is sucessfully completed");

			return new ResponseEntity<String>(body, HttpStatus.CREATED);
		} else {
			logger.debug("null value is returned");
			logger.info("response is not getting from CustomerRestApi");
			return null;
		}

	}

	public Customer getCustomerByEmail(String email) {
		logger.debug("getCustomerByEmail() method is called");

		ResponseEntity<Customer> entity = restTemplate.getForEntity(env.getProperty("GET_CUSTOMER_BY_EMAIL"),
				Customer.class, email);
		int status = entity.getStatusCode().value();
		if (status == 200) {
			Customer body = entity.getBody();

			logger.debug("getCustomerByEmail() method is ended");
			logger.info("getCustomerByEmail() method is sucessfully executed");
			return body;

		} else {
			logger.debug("null value is returned");
			logger.info("response is not getting from CustomerRestApi");
			return null;
		}

	}

	public Customer getCustomerById(Integer customerID) {
		logger.debug("getCustomerByID() method is called");
		ResponseEntity<Customer> entity = restTemplate.getForEntity(env.getProperty("GET_CUSTOMER_BY_ID") + customerID,
				Customer.class);

		int status = entity.getStatusCode().value();

		if (status == 200) {
			Customer body = entity.getBody();
			logger.debug("getCustomerByID() method is ended");
			logger.info("getCustomerByID() method is sucessfully completed");
			return body;

		} else {
			logger.debug("null value is returned");
			logger.info("response is not getting from CustomerRestApi");
			return null;
		}

	}

	public String getAllCustomerEmails() {
		logger.debug("getAllCustomerEmails() method is called");

		ResponseEntity<String> entity = restTemplate.getForEntity(env.getProperty("GET_ALL_CUSTOMER_EMAILS"),
				String.class);
		int status = entity.getStatusCode().value();
		if (status == 200) {
			String body = entity.getBody();
			logger.debug("getAllCustomerEmails() method is ended");
			logger.info("getAllCustomerEmails() method is sucessfully executed");
			return body;
		} else {
			logger.debug("null value is returned");
			logger.info("response is not getting from CustomerRestApi");
			return null;
		}

	}

}
