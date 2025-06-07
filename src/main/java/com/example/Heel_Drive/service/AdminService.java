package com.example.Heel_Drive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Heel_Drive.entity.User;
import com.example.Heel_Drive.entity.admin;
import com.example.Heel_Drive.repo.AdminRepo;
import com.example.Heel_Drive.repo.UserRepo;

@Service
public class AdminService {

	@Autowired
	private AdminRepo adminrepo;
	
	public admin saveadmindata(admin ad) {
		admin savedadmin = adminrepo.save(ad);
		return savedadmin;
	}
	
	public admin getAdminById(int id) {
    	Optional<admin> findById = adminrepo.findById(id);
    	admin ad = findById.get();
    	return ad;
    }
	
	// Method to fetch all products
    public List<admin> getAllUsers() {
        return adminrepo.findAll();
    }
	
}
