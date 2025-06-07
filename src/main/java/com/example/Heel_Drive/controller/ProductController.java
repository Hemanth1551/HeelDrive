package com.example.Heel_Drive.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Heel_Drive.entity.Product;
import com.example.Heel_Drive.entity.User;
import com.example.Heel_Drive.repo.ProductRepository;
import com.example.Heel_Drive.repo.UserRepo;
//import com.example.Heel_Rush.entity.ProductEntity;
import com.example.Heel_Drive.service.ProductService;

@RestController
public class ProductController{
	@Autowired
	public ProductService productService;
	@Autowired
	ProductRepository productrepo;
	
	public static String uploadDirectory=
			System.getProperty("user.dir") + "/src/main/webapp/product_images";

	
	
//	@PostMapping("/saveProduct")
//	public Product saveProduct(@ModelAttribute Product product,
//	                           @RequestParam("image1") MultipartFile file1,
//	                           @RequestParam("image2") MultipartFile file2,
//	                           @RequestParam("image3") MultipartFile file3,
//	                           @RequestParam("image4") MultipartFile file4,
//	                           @RequestParam("image5") MultipartFile file5) throws IOException {
//
//	    // Validate all images are provided
//	    if (file1.isEmpty() || file2.isEmpty() || file3.isEmpty() || file4.isEmpty() || file5.isEmpty()) {
//	        throw new IllegalArgumentException("All five image files are required");
//	    }
//
//	    // Ensure the upload directory exists
//	    Files.createDirectories(Paths.get(uploadDirectory));
//
//	    // Save images with unique filenames
//	    String filename1 = saveFile(file1);
//	    String filename2 = saveFile(file2);
//	    String filename3 = saveFile(file3);
//	    String filename4 = saveFile(file4);
//	    String filename5 = saveFile(file5);
//
//	    // Set image filenames in the Product entity
//	    product.setImg_1(filename1);
//	    product.setImg_2(filename2);
//	    product.setImg_3(filename3);
//	    product.setImg_4(filename4);
//	    product.setImg_5(filename5);
//
//	    // Save product
//	    return productService.saveProductData(product);
//	}
//
//	// Helper method to save a file and return the filename
//	private String saveFile(MultipartFile file) throws IOException {
//	    String originalFilename = file.getOriginalFilename();
//	    Path fileNameAndPath = Paths.get(uploadDirectory, originalFilename);
//	    Files.write(fileNameAndPath, file.getBytes());
//	    return fileNameAndPath;
//	}
	
	
//	private String saveFile(MultipartFile file) throws IOException {
//	    String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
//	    Path filePath = Paths.get(uploadDirectory, filename);
//	    Files.write(filePath, file.getBytes());
//	    return filename;
//	}
	
	
	
	@PostMapping("/saveProduct")
	public Product saveProduct(@ModelAttribute Product product,
	                           @RequestParam("image1") MultipartFile file1,
	                           @RequestParam("image2") MultipartFile file2,
	                           @RequestParam("image3") MultipartFile file3,
	                           @RequestParam("image4") MultipartFile file4,
	                           @RequestParam("image5") MultipartFile file5) throws IOException {

	    // Validate all images are provided
	    if (file1.isEmpty() || file2.isEmpty() || file3.isEmpty() || file4.isEmpty() || file5.isEmpty()) {
	        throw new IllegalArgumentException("All five image files are required");
	    }

	    // Ensure the upload directory exists
	    Files.createDirectories(Paths.get(uploadDirectory));

	    // Save images with original filenames
	    String filename1 = saveFile(file1);
	    String filename2 = saveFile(file2);
	    String filename3 = saveFile(file3);
	    String filename4 = saveFile(file4);
	    String filename5 = saveFile(file5);

	    // Set image filenames in the Product entity
	    product.setImg_1(filename1);
	    product.setImg_2(filename2);
	    product.setImg_3(filename3);
	    product.setImg_4(filename4);
	    product.setImg_5(filename5);

	    // Save product
	    return productService.saveProductData(product);
	}

	// Helper method to save a file and return the filename
	private String saveFile(MultipartFile file) throws IOException {
	    String originalFilename = file.getOriginalFilename();
	    Path fileNameAndPath = Paths.get(uploadDirectory, originalFilename);
	    Files.write(fileNameAndPath, file.getBytes());
	    return originalFilename;
	}

	
	
	 // Fetch all products
    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

//    // Fetch a product by ID
//    @GetMapping("/getProduct/{id}")
//    public Product getProduct(@RequestParam String id) {
//        Optional<Product> product = productService.getProductById(id);
//        return product.orElseThrow(() -> new RuntimeException("Product not found"));
//    }

//    @GetMapping("/getProduct/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable int id){
//    	Product product = productService.getProductById(id);
//    	return ResponseEntity.ok().body(product);
//    }
    
//    @GetMapping("/getProductImage/{id}")
//    public ResponseEntity<Resource> getProductImage(@PathVariable int id) throws IOException{
//    	Product product = productService.getProductById(id);
//    	Path imagePath = Paths.get(uploadDirectory, product.getImg_1());
//    	Resource resource = new FileSystemResource(imagePath.toFile());
//    	String contentType = Files.probeContentType(imagePath);
//    	return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).
//    			body(resource);
//	
//    }
    
    
   
    
    
    // it get 1st image of product
    @GetMapping("/getProductImage/{id}")
    public ResponseEntity<Resource> getProductImage(@PathVariable int id) throws IOException {
        Product product = productService.getProductById(id);
        Path imagePath = Paths.get(uploadDirectory, product.getImg_1());

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
    
    // it get 2nd image of product
    @GetMapping("/getProductImage2/{id}")
    public ResponseEntity<Resource> getProductImage2(@PathVariable int id) throws IOException {
        Product product = productService.getProductById(id);
        Path imagePath = Paths.get(uploadDirectory, product.getImg_2());

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
    
    
 // it get 3nd image of product
    @GetMapping("/getProductImage3/{id}")
    public ResponseEntity<Resource> getProductImage3(@PathVariable int id) throws IOException {
        Product product = productService.getProductById(id);
        Path imagePath = Paths.get(uploadDirectory, product.getImg_3());

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

 // it get 4th image of product
    @GetMapping("/getProductImage4/{id}")
    public ResponseEntity<Resource> getProductImage4(@PathVariable int id) throws IOException {
        Product product = productService.getProductById(id);
        Path imagePath = Paths.get(uploadDirectory, product.getImg_4());

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
    
 // it get 5th image of product
    @GetMapping("/getProductImage5/{id}")
    public ResponseEntity<Resource> getProductImage5(@PathVariable int id) throws IOException {
        Product product = productService.getProductById(id);
        Path imagePath = Paths.get(uploadDirectory, product.getImg_5());

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


    
    
 // Fetch all product images
    @GetMapping("/getAllProductImages")
    public ResponseEntity<List<String>> getAllProductImages() {
        List<Product> products = productService.getAllProducts();
        List<String> imageUrls = new ArrayList<>();
        
        // Loop through products to get image paths/URLs
        for (Product product : products) {
            imageUrls.add("/getProductImage/" + product.getId()); // Assuming image URL structure
        }

        return ResponseEntity.ok().body(imageUrls);
    }



    
 // Inside ProductController

//    @GetMapping("/viewProduct")
//    public String viewProduct(@RequestParam String id, Model model) {
//        Optional<Product> product = productService.getProductById(id);
//        
//        if (product.isPresent()) {
//            model.addAttribute("product", product.get());
//            return "productView";  // the name of the HTML template
//        } else {
//            throw new RuntimeException("Product not found");
//        }
//    }

    
 
    
 // Fetch all products with their details and the first image (img_1)
    @GetMapping("/getAllProductsWithImages")
    public ResponseEntity<List<ProductWithImage>> getAllProductsWithImages() {
        List<Product> products = productService.getAllProducts();
        List<ProductWithImage> productsWithImages = new ArrayList<>();
        
        // Loop through products and construct ProductWithImage objects
        for (Product product : products) {
            String firstImageUrl = "/getProductImage/" + product.getId();  // URL structure for img_1
            productsWithImages.add(new ProductWithImage(
            		product.getId(),
                    product.getProduct_id(),
                    product.getProduct_name(),
                    product.getBrand(),
                    product.getCategory(),
                    product.getColor(),
                    product.getDate(),
                    product.getDescription(),
                    product.getGender(),
                    product.getOld_price(),
                    product.getOffer_price(),
                    product.getSize(),
                    product.getStock_quantity(),
                    firstImageUrl // Use img_1 for the first image URL
            ));
        }

        return ResponseEntity.ok().body(productsWithImages);
    }

    // DTO class to return product details along with first image URL (img_1)
    public static class ProductWithImage {
    	private int id;
        private String productId;
        private String productName;
        private String brand;
        private String category;
        private String color;
        private String date;
        private String description;
        private String gender;
        private String oldPrice;
        private String offerPrice;
        private String size;
        private String stockQuantity;
        private String firstImageUrl;

        public ProductWithImage(int id,String productId, String productName, String brand, String category, String color, 
                                String date, String description, String gender, String oldPrice, String offerPrice, 
                                String size, String stockQuantity, String firstImageUrl) {
        	this.id = id;
            this.productId = productId;
            this.productName = productName;
            this.brand = brand;
            this.category = category;
            this.color = color;
            this.date = date;
            this.description = description;
            this.gender = gender;
            this.oldPrice = oldPrice;
            this.offerPrice = offerPrice;
            this.size = size;
            this.stockQuantity = stockQuantity;
            this.firstImageUrl = firstImageUrl;
        }

        // Getters and setters
        
        public String getProductId() { return productId; }
        public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getProductName() { return productName; }
        public String getBrand() { return brand; }
        public String getCategory() { return category; }
        public String getColor() { return color; }
        public String getDate() { return date; }
        public String getDescription() { return description; }
        public String getGender() { return gender; }
        public String getOldPrice() { return oldPrice; }
        public String getOfferPrice() { return offerPrice; }
        public String getSize() { return size; }
        public String getStockQuantity() { return stockQuantity; }
        public String getFirstImageUrl() { return firstImageUrl; }
    }

    
    
 // Fetch a specific product by ID
    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {  // Use Integer here
        Product product = productService.getProductById(id);
        return ResponseEntity.ok().body(product);
    }

    
    // Update a specific product by ID
//    @PutMapping("/updateProduct/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product updatedProduct) {  // Use Integer here
//        Product product = productService.updateProduct(id, updatedProduct);
//        return ResponseEntity.ok().body(product);
//    }

    // Delete a specific product by ID
//    @DeleteMapping("/deleteProduct/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {  // Use Integer here
//        productService.deleteProduct(id);
//        return ResponseEntity.noContent().build();
//    }
    @DeleteMapping("/productdel/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable int id){
		Optional<Product> user = productrepo.findById(id);
		if(user.isPresent()) {
			productrepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
    
    
  
    
	@PutMapping("/productupdate/{id}")
	public ResponseEntity<Product> updateUser(@PathVariable int id, @RequestBody Product requser){
		Optional<Product> product = productrepo.findById(id);
		if(product.isPresent()) {
			
			product.get().setProduct_name(requser.getProduct_name());
			product.get().setBrand(requser.getBrand());
			product.get().setCategory(requser.getCategory());
			product.get().setColor(requser.getColor());
			product.get().setDate(requser.getDate());
			product.get().setDescription(requser.getDescription());
			product.get().setGender(requser.getGender());
			product.get().setOld_price(requser.getOld_price());
			product.get().setOffer_price(requser.getOffer_price());
			product.get().setSize(requser.getSize());
			product.get().setStock_quantity(requser.getStock_quantity());
			
			
			return new ResponseEntity<>(productrepo.save(product.get()),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
    

    
	
}