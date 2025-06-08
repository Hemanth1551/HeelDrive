package com.example.Heel_Drive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.Heel_Drive.entity.Address;
import com.example.Heel_Drive.entity.User;
import com.example.Heel_Drive.repo.AddressRepo;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepo addressrepo;
	
	public Address saveAddressData(Address address) {
		Address savedAddress = addressrepo.save(address);
		return savedAddress;
	}

	// Method to fetch all products
    public List<Address> getAllAddress() {
        return addressrepo.findAll();
    }
    
    public Address getAddressById(int id) {
    	Optional<Address> findById = addressrepo.findById(id);
    	Address address = findById.get();
    	return address;
    }
    
//    public Address getAddressByUserId(int userid) {
//    	Optional<Address> findByUserId = addressrepo.findByUserId(userid);
//    	Address address = findByUserId.get();
//    	return address;
//    }
    
//    public Address getAddressByUserId(int userid) {
//        Optional<Address> optionalAddress = addressrepo.findByUserId(userid);
//        
//        if (optionalAddress.isPresent()) {
//            return optionalAddress.get();
//        } else {
//            throw new ResourceNotFoundException("Address not found for user ID: " + userid);
//        }
//    }
//    
//    
//    public static class ResourceNotFoundException extends RuntimeException {
//        public ResourceNotFoundException(String message) {
//            super(message);
//        }
//    }
    
    
}
