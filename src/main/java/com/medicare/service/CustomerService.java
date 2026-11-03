
package com.medicare.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medicare.entity.Customer;
import com.medicare.exceptionhandling.NotFoundException;
import com.medicare.repository.CustomerRepository;
import com.medicare.utils.SuccessResponse;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    // Save Customer
    public ResponseEntity<SuccessResponse> saveCustomer(Customer customer) {

        Customer saveCustomer = customerRepository.save(customer);

        SuccessResponse data = SuccessResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .dateTime(LocalDateTime.now())
                .message("Customer Saved Successfully")
                .data(saveCustomer)
                .build();

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    // Update Customer
    public ResponseEntity<SuccessResponse> updateCustomer(Customer customer) {

        Optional<Customer> opCustomer = customerRepository.findById(customer.getId());

        if (opCustomer.isPresent()) {

            Customer dbCustomer = opCustomer.get();

            if (customer.getEmail() != null) {
                dbCustomer.setEmail(customer.getEmail());
            }

            if (customer.getPassword() != null) {
                dbCustomer.setPassword(customer.getPassword());
            }

            Customer updateCustomer = customerRepository.save(dbCustomer);

            SuccessResponse data = SuccessResponse.builder()
                    .statusCode(HttpStatus.OK.value())
                    .dateTime(LocalDateTime.now())
                    .message("Customer Updated Successfully")
                    .data(updateCustomer)
                    .build();

            return new ResponseEntity<>(data, HttpStatus.OK);
        }

        throw new NotFoundException("Customer details not found : " + customer.getId());
    }

    // Delete Customer
    public ResponseEntity<SuccessResponse> deleteCustomer(int id) {

        Optional<Customer> opCustomer = customerRepository.findById(id);

        if (opCustomer.isPresent()) {

            Customer customer = opCustomer.get();

            customerRepository.deleteById(id);

            SuccessResponse data = SuccessResponse.builder()
                    .statusCode(HttpStatus.OK.value())
                    .dateTime(LocalDateTime.now())
                    .message("Customer Deleted Successfully")
                    .data(customer)
                    .build();

            return new ResponseEntity<>(data, HttpStatus.OK);
        }

        throw new NotFoundException("Customer details not found : " + id);
    }

    // Customer Login
    public ResponseEntity<SuccessResponse> customerLogin(String email, String password) {

        Optional<Customer> opCustomer = customerRepository.findByEmail(email);

        if (opCustomer.isPresent()) {

            Customer customer = opCustomer.get();

            if (customer.getPassword().equals(password)) {

                SuccessResponse data = SuccessResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .dateTime(LocalDateTime.now())
                        .message("Customer Login Successfully")
                        .data(customer)
                        .build();

                return new ResponseEntity<>(data, HttpStatus.OK);
            }

            throw new NotFoundException("Please check Password");
        }

        throw new NotFoundException("Customer details not found with this mail : " + email);
    }

    // Fetch Customer By ID
    public ResponseEntity<SuccessResponse> fetchCustomerById(int id) {

        Optional<Customer> opCustomer = customerRepository.findById(id);

        if (opCustomer.isPresent()) {

            Customer customer = opCustomer.get();

            SuccessResponse data = SuccessResponse.builder()
                    .statusCode(HttpStatus.OK.value())
                    .dateTime(LocalDateTime.now())
                    .message("Customer Details Fetched Successfully")
                    .data(customer)
                    .build();

            return new ResponseEntity<>(data, HttpStatus.OK);
        }

        throw new NotFoundException("Customer details not found with this id : " + id);
    }

    public ResponseEntity<SuccessResponse> fetchAllCustomers() {
        SuccessResponse data = SuccessResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .dateTime(LocalDateTime.now())
                .message("Customers fetched successfully")
                .data(customerRepository.findAll())
                .build();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}

