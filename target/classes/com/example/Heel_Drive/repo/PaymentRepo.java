package com.example.Heel_Drive.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Heel_Drive.entity.Payments;

public interface PaymentRepo extends JpaRepository<Payments, Integer> {

}
