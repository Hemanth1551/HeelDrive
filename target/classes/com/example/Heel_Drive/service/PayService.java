package com.example.Heel_Drive.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PayService {
    @Value("${razorpay.api.key}")
    private String apikey;

    @Value("${razorpay.api.secret}")
    private String apisecret;

    public String createOrder(int amount, String currency, String receiptid) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(apikey, apisecret);
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount * 100);
            orderRequest.put("currency", currency);
            orderRequest.put("receipt", receiptid); // Fixed key name

            Order order = razorpayClient.orders.create(orderRequest);
            return order.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating order: " + e.getMessage();
        }
    }
}
