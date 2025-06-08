package com.example.Heel_Drive.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Heel_Drive.entity.Orders;
import com.example.Heel_Drive.repo.OrderRepo;
import com.example.Heel_Drive.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	public OrderRepo orderrepo;
	@Autowired
	public OrderService orderservice;
	
	@PostMapping("/order")
	public Orders saveOrder(@RequestBody Orders order) {
		return orderservice.saveOrderData(order);
	}
	
	@RequestMapping("/order")
	public ResponseEntity<List<Orders>> getOrder(){
		return new ResponseEntity<>(orderrepo.findAll(), HttpStatus.OK);
	}
	
	@PutMapping("/order/{orderId}")
	public ResponseEntity<Orders> updateOrder(@PathVariable("orderId") int id, @RequestBody Orders orderreq) {
	    Optional<Orders> optionalOrder = orderrepo.findById(id);

	    if (optionalOrder.isPresent()) {
	        Orders existingOrder = optionalOrder.get();

	        // Only update if value is not null
	        if (orderreq.getStatus() != null) {
	            existingOrder.setStatus(orderreq.getStatus());
	        }

	        if (orderreq.getPaymentstatus() != null) {
	            existingOrder.setPaymentstatus(orderreq.getPaymentstatus());
	        }

	        Orders updatedOrder = orderrepo.save(existingOrder);
	        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}




}
