package com.example.Heel_Drive.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Heel_Drive.entity.Orders;
import com.example.Heel_Drive.entity.User;
import com.example.Heel_Drive.entity.admin;
import com.example.Heel_Drive.repo.AdminRepo;
import com.example.Heel_Drive.service.AdminService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class AdminController {
	
	@Autowired
	public AdminService adminservice; 
	
	@Autowired
	AdminRepo adminrepo;
	
	public static String uploadDirectory=
			System.getProperty("admin.dir") + "/src/main/webapp/admin_img";
	
	
	@PostMapping("/admin")
	public admin saveAdmin(@ModelAttribute admin ad,@RequestParam("image") MultipartFile file) throws IOException{
		
		if (file.isEmpty()) {
            throw new IllegalArgumentException("An image file is required");
        }
		Files.createDirectories(Paths.get(uploadDirectory));
		
		String filename = saveFile(file);
		
		ad.setProfile(filename);
		
		return adminservice.saveadmindata(ad);
	}
	
	// Helper method to save a file and return the filename
    private String saveFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory, originalFilename);
        Files.write(fileNameAndPath, file.getBytes());
        return originalFilename;
    }
    
    
    @PostMapping("/adminlogin")
    public ResponseEntity<String> AdminLogin(@RequestBody LoginRequest loginRequest, HttpSession session){
    	List<admin> admins = adminrepo.findAll();
    	
    	for(admin ad : admins) {
    		if(loginRequest.getEmail().equals(ad.getEmail())) {
    			if(ad.getPassword().equals(loginRequest.getPassword())) {
	                session.setAttribute("adminid", ad.getId()); // Store Integer userId instead of String username
	                session.setAttribute("adminname", ad.getUsername());
	                session.setAttribute("adminprofile", ad.getProfile());
	                return ResponseEntity.ok("Login successful");
	            } else {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
	            
    			}
    		}
    	}
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("admin not found");

    }
    
    
    // DTO for request
    public static class LoginRequest {
        private String password;
        private String email;

        public String getEmail() {
			// TODO Auto-generated method stub
			return email;
		}
//		public void setUsername(String username) { this.username = username; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
    
    @GetMapping("/admininfo")
    public ResponseEntity<?> getAdminInfo(HttpSession session) {
        Integer adminId = (Integer) session.getAttribute("adminid");
        String adminName = (String) session.getAttribute("adminname");
        String adminProfile = (String) session.getAttribute("adminprofile");

        if (adminId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("id", adminId);
        response.put("name", adminName);
        response.put("profile", adminProfile);

        return ResponseEntity.ok(response);
    }
    
    
	// it get 1st image of product
    @GetMapping("/getAdminImage/{id}")
    public ResponseEntity<Resource> getUserImage(@PathVariable int id) throws IOException {
        admin ad = adminservice.getAdminById(id);
        Path imagePath = Paths.get(uploadDirectory, ad.getProfile());

        // Check if the file exists
        if (!Files.exists(imagePath)) {
            throw new FileNotFoundException("File not found: " + imagePath.toString());
        }

        Resource resource = new FileSystemResource(imagePath.toFile());
        String contentType = Files.probeContentType(imagePath);
        return ResponseEntity.ok()
                             .contentType(MediaType.parseMediaType(contentType))
                             .body(resource);
    }
    
    @PostMapping("/adminlogout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Clears all session data
        return ResponseEntity.ok("Logged out");
    }

    @GetMapping("/admins")
	public ResponseEntity<List<admin>> getUsers(){
		return new ResponseEntity<>(adminrepo.findAll(),HttpStatus.OK);
	}
    
    
    @PutMapping("/admin/{adminId}")
	public ResponseEntity<admin> updateOrder(@PathVariable("adminId") int id, @RequestBody admin adminreq) {
	    Optional<admin> optionalAdmin = adminrepo.findById(id);

	    if (optionalAdmin.isPresent()) {
	        admin existingAdmin = optionalAdmin.get();

	        // Only update if value is not null
	        if (adminreq.getUsername() != null) {
	        	existingAdmin.setUsername(adminreq.getUsername());
	        }

	        if (adminreq.getEmail() != null) {
	        	existingAdmin.setEmail(adminreq.getEmail());
	        }

	        if (adminreq.getPassword() != null) {
	        	existingAdmin.setPassword(adminreq.getPassword());
	        }
	        
	        if (adminreq.getPhone() != null) {
	        	existingAdmin.setPhone(adminreq.getPhone());
	        }
	        
	        if (adminreq.getUpdateat() != null) {
	        	existingAdmin.setUpdateat(adminreq.getUpdateat());
	        }
	        
	        admin updatedAdmin = adminrepo.save(existingAdmin);
	        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}


}
