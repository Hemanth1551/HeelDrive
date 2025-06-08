package com.example.Heel_Drive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Heel_Drive.entity.Address;
import com.example.Heel_Drive.repo.AddressRepo;
import com.example.Heel_Drive.service.AddressService;

@RestController
@RequestMapping("/api")
public class AddressController {
	
	@Autowired
	public AddressService addressservice;
	@Autowired
	AddressRepo addressrepo;
	
	@PostMapping("/address")
    public Address saveAddress(@RequestBody Address address) {
        // Save product
        return addressservice.saveAddressData(address);
	}
	
	
	@GetMapping("/address")
	public ResponseEntity<List<Address>> getAddress(){
		return new ResponseEntity<>(addressrepo.findAll(), HttpStatus.OK); 
	}
	
	@GetMapping("/address/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable Integer id){
		Address address = addressservice.getAddressById(id);
		return ResponseEntity.ok().body(address);
	}
	
//	@GetMapping("/address/{userid}")
//    public ResponseEntity<Address> getAddressByUserId(@PathVariable Integer userid) {
//        Address address = addressservice.getAddressByUserId(userid);
//        return ResponseEntity.ok().body(address);
//    }
//	

	

}
