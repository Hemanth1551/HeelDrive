package com.example.Heel_Drive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Heel_Drive.entity.Cart;
import com.example.Heel_Drive.repo.CartRepo;

@Service
public class CartService {
	
	@Autowired
	private CartRepo cartrepo;
	
	public Cart saveCartData(Cart cart) {
		Cart savedCart = cartrepo.save(cart);
		return savedCart;
	}
	
	public List<Cart> getAllCart(){
		return cartrepo.findAll();
	}

}
