package com.example.Heel_Drive.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Heel_Drive.entity.Cart;
import com.example.Heel_Drive.repo.CartRepo;
import com.example.Heel_Drive.service.CartService;

@RestController
@RequestMapping("/api")
public class CartController {
	
	@Autowired
	public CartRepo cartrepo;
	@Autowired
	public CartService cartservice;
	
	@PostMapping("/cart")
	public Cart SaveCart(@RequestBody Cart cart) {
		return cartservice.saveCartData(cart);
	}
	
	@GetMapping("/cart")
	public ResponseEntity<List<Cart>> getCart(){
		return new ResponseEntity<>(cartrepo.findAll(), HttpStatus.OK);
	}
	
	@PutMapping("/cart/{cartId}")
	public ResponseEntity<Cart> updateCart(@PathVariable("cartId") int id, @RequestBody Cart orderreq) {
	    // Find the existing cart item by ID
	    Optional<Cart> optionalOrder = cartrepo.findById(id);

	    // Check if the cart item exists
	    if (optionalOrder.isPresent()) {
	        Cart existingOrder = optionalOrder.get();
	        
	        // Update the quantity with the new value from the request body
	        existingOrder.setQuantity(orderreq.getQuantity());

	        // Save the updated cart item back to the repository
	        Cart updatedOrder = cartrepo.save(existingOrder);
	        
	        // Return the updated cart item with a 200 OK status
	        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
	    } else {
	        // Return a 404 Not Found status if the cart item doesn't exist
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/cart/{cartId}")
	public ResponseEntity<Void> deleteCartItem(@PathVariable int cartId) {
	    Optional<Cart> cartItem = cartrepo.findById(cartId);
	    if (cartItem.isPresent()) {
	        cartrepo.deleteById(cartId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}


}
