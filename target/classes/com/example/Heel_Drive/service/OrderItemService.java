package com.example.Heel_Drive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Heel_Drive.entity.OrderItem;
import com.example.Heel_Drive.repo.OrderItemRepo;

@Service
public class OrderItemService {
	
	@Autowired
	public OrderItemRepo orderitemrepo;
	
	public OrderItem saveOrderItemData(OrderItem orderitem) {
		OrderItem SavedOrder = orderitemrepo.save(orderitem);
		return SavedOrder;
	}
	
	public List<OrderItem> getOrders(){
		return orderitemrepo.findAll();
	}
}
