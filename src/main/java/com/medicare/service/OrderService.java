package com.medicare.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medicare.entity.Drug;
import com.medicare.entity.Order;
import com.medicare.exceptionhandling.NotFoundException;
import com.medicare.repository.DrugRepository;
import com.medicare.repository.OrderRepository;
import com.medicare.utils.SuccessResponse;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final DrugRepository drugRepo;

    public OrderService(OrderRepository orderRepository, CustomerService customerService, DrugRepository drugRepo) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.drugRepo = drugRepo;
    }

    @Transactional
    public ResponseEntity<SuccessResponse> orderDrugs(int custId, List<String> drugNames) {
        customerService.fetchCustomerById(custId);
        if (drugNames == null || drugNames.isEmpty()) {
            throw new NotFoundException("At least one drug is required");
        }

        double totalAmount = 0;
        for (String name : drugNames) {
            Drug drug = drugRepo.findByNameIgnoreCase(name)
                    .orElseThrow(() -> new NotFoundException("Drug not found: " + name));
            if (drug.isBanned()) {
                throw new NotFoundException("Drug is unavailable: " + name);
            }
            if (drug.getQuantity() < 1) {
                throw new NotFoundException("Drug out of stock: " + name);
            }
            drug.setQuantity(drug.getQuantity() - 1);
            drugRepo.save(drug);
            totalAmount += drug.getPrice();
        }

        Order order = new Order();
        order.setCustomerid(custId);
        order.setOrderamount(totalAmount);
        Order savedOrder = orderRepository.save(order);
        SuccessResponse body = SuccessResponse.builder().statusCode(HttpStatus.CREATED.value())
                .dateTime(LocalDateTime.now()).message("Order placed successfully").data(savedOrder).build();
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }
}
