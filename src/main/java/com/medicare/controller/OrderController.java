package com.medicare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.service.OrderService;
import com.medicare.utils.SuccessResponse;

@RestController
@RequestMapping("/payment")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	
	@PostMapping("/orderplaced")
	public ResponseEntity<SuccessResponse> drugPayment(@RequestParam int custId,@RequestParam List<String> drugName)
	{
		return orderService.orderDrugs(custId, drugName);
	}

}
