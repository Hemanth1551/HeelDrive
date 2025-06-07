package com.example.Heel_Drive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Heel_Drive.entity.User;
import com.example.Heel_Drive.repo.UserRepo;
import com.example.Heel_Drive.service.UserServices.UserNotFoundException;

@Service
public class UserServices {

	@Autowired
	private UserRepo userr;
	
	public User saveProductData(User user) {
		User savedProduct = userr.save(user);
		return savedProduct;
	}
	
	
	// Method to fetch all products
    public List<User> getAllUsers() {
        return userr.findAll();
    }
	
	public User getUserById(int id) {
    	Optional<User> findById = userr.findById(id);
    	User user = findById.get();
    	return user;
    }
	
	
	// Update a specific product by ID
    public User updateUser(int id, User updateduser) {
        Optional<User> existinguser = userr.findById(id);
        if (existinguser.isPresent()) {
            User user = existinguser.get();
            user.setId(updateduser.getId());
            user.setUsername(updateduser.getUsername());
            user.setEmail(updateduser.getEmail());
            user.setPassword(updateduser.getPassword());
            user.setPhone(updateduser.getPhone());
            user.setCreated(updateduser.getCreated());
            user.setUpdateat(updateduser.getUpdateat());
            user.setProfile(updateduser.getProfile());

           
            // Save and return the updated product
            return userr.save(user);
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }
    
    
 // Custom exception handling for ProductNotFoundException
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
	
    
	
}
