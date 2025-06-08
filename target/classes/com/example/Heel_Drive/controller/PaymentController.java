package com.example.Heel_Drive.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Heel_Drive.entity.Payments;
import com.example.Heel_Drive.repo.PaymentRepo;
import com.example.Heel_Drive.service.PayService;
import com.example.Heel_Drive.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
	
    @Autowired
    private PayService payservice;
   
    @Autowired
    private PaymentService paymentservice;
    
    @Autowired
    private PaymentRepo paymentrepo;

	
    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestParam int amount, @RequestParam String currency) {
        try {
            String order = payservice.createOrder(amount, currency, "receipt_100");
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\": \"Failed to create order: " + e.getMessage() + "\"}");
        }
    }
    
    @PostMapping("/payment")
    public Payments savePayment(@RequestBody Payments payments) {
    	return paymentservice.savePaymenstData(payments);
    }
    
    @GetMapping("/payment")
    public ResponseEntity<List<Payments>> getPayments(){
    	return new ResponseEntity<>(paymentrepo.findAll(), HttpStatus.OK);
    }
    
    
   

}
