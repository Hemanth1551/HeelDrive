package com.example.Heel_Drive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Heel_Drive.entity.Product;
//import com.example.Heel_Rush.entity.ProductEntity;
import com.example.Heel_Drive.repo.ProductRepository;

@Service
public class ProductService{
	@Autowired
	private ProductRepository productRepository;
	
	public Product saveProductData(Product product) {
		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}
	
	// Method to fetch all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Method to fetch a product by ID
//    public Optional<Product> getProductById(String id) {
//        try {
//            Integer productId = Integer.parseInt(id); // Convert String to Integer
//            return productRepository.findById(productId);
//        } catch (NumberFormatException e) {
//            return Optional.empty(); // Handle invalid ID format
//        }
//    }
    
    public Product getProductById(int id) {
    	Optional<Product> findById = productRepository.findById(id);
    	Product product = findById.get();
    	return product;
    }

 // Update a specific product by ID
    public Product updateProduct(int id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setProduct_id(updatedProduct.getProduct_id());
            product.setProduct_name(updatedProduct.getProduct_name());
            product.setBrand(updatedProduct.getBrand());
            product.setCategory(updatedProduct.getCategory());
            product.setColor(updatedProduct.getColor());
            product.setDate(updatedProduct.getDate());
            product.setDescription(updatedProduct.getDescription());
            product.setGender(updatedProduct.getGender());
            product.setOld_price(updatedProduct.getOld_price());
            product.setOffer_price(updatedProduct.getOffer_price());
            product.setSize(updatedProduct.getSize());
            product.setStock_quantity(updatedProduct.getStock_quantity());
            product.setImg_1(updatedProduct.getImg_1());
            product.setImg_2(updatedProduct.getImg_2());
            product.setImg_3(updatedProduct.getImg_3());
            product.setImg_4(updatedProduct.getImg_4());
            product.setImg_5(updatedProduct.getImg_5());
            // Save and return the updated product
            return productRepository.save(product);
        } else {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
    }

    // Delete a product by ID
//    public void deleteProduct(int id) {
//        Optional<Product> existingProduct = productRepository.findById(id);
//        if (existingProduct.isPresent()) {
//            productRepository.delete(existingProduct.get());
//        } else {
//            throw new ProductNotFoundException("Product not found with id: " + id);
//        }
//    }

    // Custom exception handling for ProductNotFoundException
//    public static class ProductNotFoundException extends RuntimeException {
//        public ProductNotFoundException(String message) {
//            super(message);
//        }
//    }
 // Delete a product by ID
    public void deleteProduct(Integer id) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            productRepository.deleteById(id);  // Delete product
        } else {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
    }

    // Custom exception handling for ProductNotFoundException
    public static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

}