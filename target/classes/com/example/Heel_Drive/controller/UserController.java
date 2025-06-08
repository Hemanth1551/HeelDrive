package com.example.Heel_Drive.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.DeleteMapping;
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

import com.example.Heel_Drive.controller.UserController.LoginRequest;
import com.example.Heel_Drive.controller.UserController.UserWithImage;
import com.example.Heel_Drive.entity.User;
import com.example.Heel_Drive.entity.admin;
import com.example.Heel_Drive.repo.UserRepo;
import com.example.Heel_Drive.service.UserServices;

import jakarta.servlet.http.HttpSession;




@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	public UserServices userservices;
	@Autowired
	UserRepo userrepo;

	
	
//	@PostMapping("/userlogin")
//	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
//	    List<User> users = userrepo.findAll();
//	    for (User user : users) {
//	        if (user.getUsername().equals(loginRequest.getUsername())) {
//	            if (user.getPassword().equals(loginRequest.getPassword())) {
//	            	session.setAttribute("userId", user.getUsername());
//	                return ResponseEntity.ok("Login successful");
//	            } else {
//	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
//	            }
//	        }
//	    }
//	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
//	}
	
	@PostMapping("/userlogin")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
	    List<User> users = userrepo.findAll();
	    for (User user : users) {
	        if (user.getEmail().equals(loginRequest.getEmail())) {
	            if (user.getPassword().equals(loginRequest.getPassword())) {
	                session.setAttribute("userId", user.getId());
	                session.setAttribute("username", user.getUsername());
	                // Store Integer userId instead of String username
	                return ResponseEntity.ok("Login successful");
	            } else {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
	            }
	        }
	    }
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
	}


    // DTO for request
    public static class LoginRequest {
//        private String username;
        private String password;
        private String email;

//        public String getUsername() { return username; }
        public Object getEmail() {
			// TODO Auto-generated method stub
			return email;
		}
//		public void setUsername(String username) { this.username = username; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
	
	
	//@PostMapping("/users")
	//public ResponseEntity<User> saveUser(@RequestBody User user) {
		//return new ResponseEntity<>(userrepo.save(user),HttpStatus.CREATED);
	//}
    public static String uploadDirectory=
			System.getProperty("user.dir") + "/src/main/webapp/user_images";
    
    
//    @GetMapping("/session-userid")
//    public ResponseEntity<Integer> getSessionUserId(HttpSession session) {
//        Integer userId = (Integer) session.getAttribute("userId");
//        if (userId == null) {
//            return ResponseEntity.status(401).body(null);
//        }
//        return ResponseEntity.ok(userId);
//    }
    
    @GetMapping("/session-userid")
    public ResponseEntity<?> getSessionUserId(HttpSession session) {
        Object userId = session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No active session");
        }
        
        if (userId instanceof Integer) {
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid session data type");
        }
    }
    
    
    @GetMapping("/userinfo")
    public ResponseEntity<?> getAdminInfo(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        String userName = (String) session.getAttribute("username");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("id", userId);
        response.put("name", userName);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Destroy the session
        return ResponseEntity.ok("Logout successful");
    }
    
    
    @PostMapping("/users")
    public User saveProduct(@ModelAttribute User user,
                               @RequestParam("image") MultipartFile file) throws IOException {

        // Validate the image is provided
        if (file.isEmpty()) {
            throw new IllegalArgumentException("An image file is required");
        }

        // Ensure the upload directory exists
        Files.createDirectories(Paths.get(uploadDirectory));

        // Save image with original filename
        String filename = saveFile(file);

        // Set image filename in the Product entity
        user.setProfile(filename);

        // Save product
        return userservices.saveProductData(user);
    }

    // Helper method to save a file and return the filename
    private String saveFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory, originalFilename);
        Files.write(fileNameAndPath, file.getBytes());
        return originalFilename;
    }
    
    
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<>(userrepo.findAll(),HttpStatus.OK);
	}
	
//	@GetMapping("/users/{id}")
//	public ResponseEntity<User> getUser(@PathVariable long id){
//		Optional<User> user = userrepo.findById(id);
//		if(user.isPresent()) {
//			return new ResponseEntity<>(user.get(),HttpStatus.OK);
//		}
//		else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
	
	
//	@PutMapping("/users/{id}")
//	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User requser){
//		Optional<User> user = userrepo.findById(id);
//		if(user.isPresent()) {
//			
//			user.get().setUsername(requser.getUsername());
//			user.get().setEmail(requser.getEmail());
//			user.get().setPassword(requser.getPassword());
//			return new ResponseEntity<>(userrepo.save(user.get()),HttpStatus.OK);
//		}
//		else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
	
	
//	@DeleteMapping("/users/{id}")
//	public ResponseEntity<Void> deleteUser(@PathVariable long id){
//		Optional<User> user = userrepo.findById(id);
//		if(user.isPresent()) {
//			userrepo.deleteById(id);
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
	
	
	// it get 1st image of product
    @GetMapping("/getUserImage/{id}")
    public ResponseEntity<Resource> getUserImage(@PathVariable int id) throws IOException {
        User user = userservices.getUserById(id);
        Path imagePath = Paths.get(uploadDirectory, user.getProfile());

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
    
    
    
 // Fetch a specific product by ID
    @GetMapping("/getUsers/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {  // Use Integer here
        User user = userservices.getUserById(id);
        return ResponseEntity.ok().body(user);
    }
    
    
    
    
    
    
    
 // Fetch all products with their details and the first image (img_1)
    @GetMapping("/getAllUsersWithImages")
    public ResponseEntity<List<UserWithImage>> getAllusersWithImages() {
        List<User> users = userservices.getAllUsers();
        List<UserWithImage> usersWithImages = new ArrayList<>();
        

		// Loop through products and construct ProductWithImage objects
        for (User user : users) {
            String profile = "/api/getUserImage/" + user.getId();  // URL structure for img_1
            usersWithImages.add(new UserWithImage(
            		user.getId(),
            		user.getUsername(),
            		user.getEmail(),
                    user.getPassword(),
                    user.getCreated(),
                    user.getPhone(),
                    user.getUpdateat(),
                    profile // Use profile for the first image URL
            ));
        }

        return ResponseEntity.ok().body(usersWithImages);
    }

    // DTO class to return product details along with first image URL (img_1)
    public static class UserWithImage {
    	private int id;
        private String username;
        private String email;
        private String password;
        private String phone;
        private String created;
        private String updateat;
        private String profile;
        
        


		public UserWithImage(int id,String username, String email, String password, String phone, String created, 
                                String updateat, String profile) {
        	this.id = id;
            this.username = username;
            this.email = email;
            this.password = password;
            this.phone = phone;
            this.created = created;
            this.updateat = updateat;
            this.profile = profile;
        }

        // Getters and setters
        
        //public String getProductId() { return productId; }
        public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		

		public String getUsername() {
			return username;
		}
		public String getEmail() {
			return email;
		}

		public String getPassword() {
			return password;
		}


		public String getPhone() {
			return phone;
		}

		public String getCreated() {
			return created;
		}

		public String getUpdateat() {
			return updateat;
		}


		public String getProfile() {
			return profile;
		}

    }
    
    //delete user by id
    @DeleteMapping("/userdel/{id}")
	public ResponseEntity<Void> deleteuser(@PathVariable int id){
		Optional<User> user = userrepo.findById(id);
		if(user.isPresent()) {
			userrepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
    
    /*may not use */
    @PutMapping("/userupdate/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User requser){
		Optional<User> user = userrepo.findById(id);
		if(user.isPresent()) {
			
			user.get().setUsername(requser.getUsername());
			user.get().setEmail(requser.getEmail());
			user.get().setPassword(requser.getPassword());
			user.get().setCreated(requser.getCreated());
			user.get().setPhone(requser.getPhone());
			user.get().setUpdateat(requser.getUpdateat());
			
			
			
			return new ResponseEntity<>(userrepo.save(user.get()),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
    
    

    
    
    
    
    
    
    
    @PutMapping("/user/{userId}")
	public ResponseEntity<User> updateOrder(@PathVariable("userId") int id, @RequestBody User adminreq) {
	    Optional<User> optionalUser = userrepo.findById(id);

	    if (optionalUser.isPresent()) {
	    	User existinguser = optionalUser.get();

	        // Only update if value is not null
	        if (adminreq.getUsername() != null) {
	        	existinguser.setUsername(adminreq.getUsername());
	        }

	        if (adminreq.getEmail() != null) {
	        	existinguser.setEmail(adminreq.getEmail());
	        }

	        if (adminreq.getPassword() != null) {
	        	existinguser.setPassword(adminreq.getPassword());
	        }
	        
	        if (adminreq.getPhone() != null) {
	        	existinguser.setPhone(adminreq.getPhone());
	        }
	        
	        if (adminreq.getUpdateat() != null) {
	        	existinguser.setUpdateat(adminreq.getUpdateat());
	        }
	        
	        User updatedAdmin = userrepo.save(existinguser);
	        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}


}

