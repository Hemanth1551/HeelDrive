package com.example.Heel_Drive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Heel_Drive.entity.Orders;
import com.example.Heel_Drive.repo.OrderRepo;

@Service
public class OrderService {
	
	@Autowired
	public OrderRepo orderrepo;
	
	public Orders saveOrderData(Orders order) {
		Orders SavedOrder = orderrepo.save(order);
		return SavedOrder;
	}
	
	public List<Orders> getOrders(){
		return orderrepo.findAll();
	}

}
