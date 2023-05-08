package com.example.miniprojetspring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.miniprojetspring.entities.Product;
import com.example.miniprojetspring.exception.ResourceNotFoundException;
import com.example.miniprojetspring.repository.ProductRepository;


@RestController
@RequestMapping("/api/product/")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	@Autowired
	private ProductRepository productRepo;
	
	
	//create Product
	@PostMapping("/create")
	public Product createProduct(@Valid @RequestBody Product Product) {
	    return productRepo.save(Product);
	}
	
	//get all Productes 
	@GetMapping("/Productes")
	public List<Product> getAllProductes(){
		return productRepo.findAll();
	}
	
  // Single item
  
  @GetMapping("/{id}")
  Product getSingleProduct(@PathVariable Long id) {
    
    return productRepo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("We couldn't found any product with the specified id!"));
  }
	
  
  
  @PutMapping("/modifyProduct/{id}")
  Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {
    
    return productRepo.findById(id)
      .map(Product -> {
    	  Product.setDescription(newProduct.getDescription());
    	  Product.setName(newProduct.getName());
    	  Product.setPrice(newProduct.getPrice());
        return productRepo.save(Product);
      })
      .orElseGet(() -> {
    	  newProduct.setId(id);
        return productRepo.save(newProduct);
      });
  }
  

  @DeleteMapping("/deleteProduct/{id}")
  public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
	  try{
		  productRepo.deleteById(id);
	    return ResponseEntity.ok("Product has been deleted successfully!");      
	   }
	   catch (EmptyResultDataAccessException e){
	      return ResponseEntity.notFound().build();
	  }                                
	 }
  
}
