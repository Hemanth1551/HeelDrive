package com.example.Heel_Drive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Heel_Drive.entity.Payments;
import com.example.Heel_Drive.repo.PaymentRepo;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepo paymentrepo;
	
	public Payments savePaymenstData(Payments payment) {
		Payments savedPayment = paymentrepo.save(payment);
		return savedPayment;
	}
	
	public List<Payments> AllPayments(){
		return paymentrepo.findAll();
	}

}
