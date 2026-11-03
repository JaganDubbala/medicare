package com.medicare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.entity.Customer;
import com.medicare.service.CustomerService;
import com.medicare.utils.SuccessResponse;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/register")
	public ResponseEntity<SuccessResponse> saveCustomer(@RequestBody Customer customer)
	{
		return customerService.saveCustomer(customer);
	}

	@PutMapping("/update")
	public ResponseEntity<SuccessResponse> updateCustomer(@RequestBody Customer customer)
	{
		return customerService.updateCustomer(customer);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<SuccessResponse> deleteCustomer(@RequestParam int id)
	{
		return customerService.deleteCustomer(id);
	}

	@GetMapping("/fetch")
	public ResponseEntity<SuccessResponse> fetchCustomerById(@RequestParam int id)
	{
		return customerService.fetchCustomerById(id);
	}

	@GetMapping("/fetchAll")
	public ResponseEntity<SuccessResponse> fetchAllCustomers()
	{
		return customerService.fetchAllCustomers();
	}

	@GetMapping("/login")
	public ResponseEntity<SuccessResponse> loginCustomer(@RequestParam String email,
														   @RequestParam String password)
	{
		return customerService.customerLogin(email, password);
	}
}