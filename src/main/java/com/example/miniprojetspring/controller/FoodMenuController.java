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


import com.example.miniprojetspring.entities.FoodMenu;
import com.example.miniprojetspring.exception.ResourceNotFoundException;
import com.example.miniprojetspring.repository.FoodMenuRepository;

@RestController
@RequestMapping("/api/foodmenu/")
@CrossOrigin(origins = "http://localhost:4200")
public class FoodMenuController {

	@Autowired
	private FoodMenuRepository foodmenuRepository;
	
	//create foodmenu
	@PostMapping("/create")
	public FoodMenu createFoodMenu(@Valid @RequestBody FoodMenu food) {
	    return foodmenuRepository.save(food);
	}
	
	//get all foodmenu 
	@GetMapping("/foodmenus")
	public List<FoodMenu> getAllFoodMenu(){
		return foodmenuRepository.findAll();
	}
	
	// Single item
	  @GetMapping("/{id}")
	  FoodMenu getSingleFoodMenu(@PathVariable Long id) {
	    
	    return foodmenuRepository.findById(id)
	      .orElseThrow(() -> new ResourceNotFoundException("We couldn't found any food menu with the specified id!"));
	  }
	
	
	  @PutMapping("/modifyFoodMenu/{id}")
	  FoodMenu replaceFoodMenu(@RequestBody FoodMenu newfoodmenu, @PathVariable Long id) {
	    
	    return foodmenuRepository.findById(id)
	      .map(foodmenu -> {
	    	  foodmenu.setDishes(newfoodmenu.getDishes());
	    	  foodmenu.setName(newfoodmenu.getName());
	        return foodmenuRepository.save(foodmenu);
	      })
	      .orElseGet(() -> {
	    	  newfoodmenu.setId(id);
	        return foodmenuRepository.save(newfoodmenu);
	      });
	  }
	
	
	  @DeleteMapping("/deleteFoodMenu/{id}")
	  public ResponseEntity<?> deleteFoodMenu(@PathVariable Long id) {
		  try{
			  foodmenuRepository.deleteById(id);
		    return ResponseEntity.ok("Food menu has been deleted successfully!");      
		   }
		   catch (EmptyResultDataAccessException e){
		      return ResponseEntity.notFound().build();
		  }                                
		 }
	
	
}
