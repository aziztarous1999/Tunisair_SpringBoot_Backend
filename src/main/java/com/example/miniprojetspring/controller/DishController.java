package com.example.miniprojetspring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.miniprojetspring.entities.Dish;
import com.example.miniprojetspring.exception.ResourceNotFoundException;
import com.example.miniprojetspring.repository.DishRepository;


@RestController
@RequestMapping("/api/dish/")
public class DishController {

	@Autowired
	private DishRepository dishRepository;
	
	
	//create dish
	@PostMapping("/create")
	public Dish createDish(@Valid @RequestBody Dish dish) {
	    return dishRepository.save(dish);
	}
	
	//get all dishes 
	@GetMapping("/dishes")
	public List<Dish> getAllDishes(){
		return dishRepository.findAll();
	}
	
  // Single item
  
  @GetMapping("/{id}")
  Dish getSingleDish(@PathVariable Long id) {
    
    return dishRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("We couldn't found any dish with the specified id!"));
  }
	
  
  
  @PutMapping("/modifyDish/{id}")
  Dish replaceDish(@RequestBody Dish newdish, @PathVariable Long id) {
    
    return dishRepository.findById(id)
      .map(dish -> {
    	  dish.setDescription(newdish.getDescription());
    	  dish.setName(newdish.getName());
    	  dish.setPrice(newdish.getPrice());
        return dishRepository.save(dish);
      })
      .orElseGet(() -> {
    	  newdish.setId(id);
        return dishRepository.save(newdish);
      });
  }
  

  @DeleteMapping("/deleteDish/{id}")
  public ResponseEntity<?> deleteDish(@PathVariable Long id) {
	  try{
		  dishRepository.deleteById(id);
	    return ResponseEntity.ok("Dish has been deleted successfully!");      
	   }
	   catch (EmptyResultDataAccessException e){
	      return ResponseEntity.notFound().build();
	  }                                
	 }
  
}
