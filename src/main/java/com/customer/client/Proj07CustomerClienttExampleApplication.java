package com.customer.client;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;

import com.customer.client.model.Customer;
import com.customer.client.service.CustomerClientService;

@SpringBootApplication
public class Proj07CustomerClienttExampleApplication {

	public static void main(String[] args) {
		CustomerClientService service = null;
		Scanner sc = null;
		String customerEmail = null;
		ConfigurableApplicationContext ctx = SpringApplication.run(Proj07CustomerClienttExampleApplication.class, args);
		service = ctx.getBean(CustomerClientService.class);
		
		  Customer customer = service.getCustomerByEmail("naveen@gmail.com");
		  System.out.println(customer); Customer customerById =
		  service.getCustomerById(23); System.out.println(customerById); String
		  allCustomerEmails = service.getAllCustomerEmails();
		  System.out.println(allCustomerEmails);
		 
		/*
		 * Customer customer2 = new Customer(); sc = new Scanner(System.in);
		 * System.out.println("Enter customer email"); customerEmail = sc.next();
		 * customer2.setCustomerEmail(customerEmail);
		 * System.out.println("Enter customer name"); String customerName = sc.next();
		 * customer2.setCustomerName(customerName); ResponseEntity<String> details =
		 * service.addCustomerDetails(customer2); System.out.println(details.getBody() +
		 * "==" + details.getStatusCodeValue());
		 */
		sc.close();

	}

}
