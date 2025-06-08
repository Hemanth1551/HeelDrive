package com.example.Heel_Drive.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Heel_Drive.entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer>{

}
