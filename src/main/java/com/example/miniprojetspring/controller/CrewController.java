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

import com.example.miniprojetspring.entities.Crew;
import com.example.miniprojetspring.exception.ResourceNotFoundException;
import com.example.miniprojetspring.repository.CrewRepository;

@RestController
@RequestMapping("/api/crew/")
public class CrewController {

	@Autowired
	private CrewRepository crewRepository;
	
	//create crew
	@PostMapping("/create")
	public Crew createCrew(@Valid @RequestBody Crew crew) {
	    return crewRepository.save(crew);
	}
	
	//get all crews 
	@GetMapping("/crews")
	public List<Crew> getAllCrew(){
		return crewRepository.findAll();
	}
	
	  // Single item
	  @GetMapping("/{id}")
	  Crew getSingleCrew(@PathVariable Long id) {
	    
	    return crewRepository.findById(id)
	      .orElseThrow(() -> new ResourceNotFoundException("We couldn't found any crew with the specified id!"));
	  }
	  
	  
	  
	  @PutMapping("/modifyCrew/{id}")
	  Crew replaceCrew(@RequestBody Crew newcrew, @PathVariable Long id) {
	    
	    return crewRepository.findById(id)
	      .map(crew -> {
	    	  crew.setEmployeeID(newcrew.getEmployeeID());
	    	  crew.setName(newcrew.getName());
	    	  crew.setRole(newcrew.getRole());
	    	  crew.setYearsOfExperience(newcrew.getYearsOfExperience());
	        return crewRepository.save(crew);
	      })
	      .orElseGet(() -> {
	    	  newcrew.setId(id);
	        return crewRepository.save(newcrew);
	      });
	  }
	  
	  
	  @DeleteMapping("/deleteCrew/{id}")
	  public ResponseEntity<?> deleteCrew(@PathVariable Long id) {
		  try{
			  crewRepository.deleteById(id);
		    return ResponseEntity.ok("Crew has been deleted successfully!");      
		   }
		   catch (EmptyResultDataAccessException e){
		      return ResponseEntity.notFound().build();
		  }                                
		 }
		
	
}
