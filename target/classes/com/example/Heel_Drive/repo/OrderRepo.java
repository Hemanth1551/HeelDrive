package com.example.Heel_Drive.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Heel_Drive.entity.Orders;

public interface OrderRepo extends JpaRepository<Orders, Integer>{

}
