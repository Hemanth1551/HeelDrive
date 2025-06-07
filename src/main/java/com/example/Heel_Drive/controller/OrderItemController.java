package com.example.Heel_Drive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Heel_Drive.entity.OrderItem;
import com.example.Heel_Drive.repo.OrderItemRepo;
import com.example.Heel_Drive.service.OrderItemService;

@RestController
@RequestMapping("/api")
public class OrderItemController {
	
	@Autowired
	public OrderItemRepo orderitemrepo;
	@Autowired
	public OrderItemService orderitemservice;
	
	@PostMapping("/orderitem")
	public OrderItem saveOrderItem(@RequestBody OrderItem orderitem) {
		return orderitemservice.saveOrderItemData(orderitem);
	}
	
	@RequestMapping("/orderitem")
	public ResponseEntity<List<OrderItem>> getOrderItem(){
		return new ResponseEntity<>(orderitemrepo.findAll(), HttpStatus.OK);
	}
}
