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

import com.example.miniprojetspring.entities.Airport;
import com.example.miniprojetspring.exception.ResourceNotFoundException;
import com.example.miniprojetspring.repository.AirportRepository;

@RestController
@RequestMapping("/api/airport/")
@CrossOrigin(origins = "http://localhost:4200")
public class AirportController {

	@Autowired
	private AirportRepository airportRepository;
	
	

	//create airport
	@PostMapping("/create")
	public Airport createAirport(@Valid @RequestBody Airport airport) {
	    return airportRepository.save(airport);
	}
	
	//get all airports 
	@GetMapping("/airports")
	public List<Airport> getAllAirports(){
		return airportRepository.findAll();
	}
	
  // Single item
  @GetMapping("/{id}")
  Airport getSingleAirport(@PathVariable Long id) {
    
    return airportRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("We couldn't found any airport with the specified id!"));
  }
	
  
  
  @PutMapping("/modifyAirport/{id}")
  Airport replaceAirport(@RequestBody Airport newairport, @PathVariable Long id) {
    
    return airportRepository.findById(id)
      .map(airport -> {
    	  airport.setName(newairport.getName());
    	  airport.setCountry(newairport.getCountry());
    	  airport.setCity(newairport.getCity());
        return airportRepository.save(airport);
      })
      .orElseGet(() -> {
    	  newairport.setId(id);
        return airportRepository.save(newairport);
      });
  }
  

  @DeleteMapping("/deleteAirport/{id}")
  public ResponseEntity<?> deleteAirport(@PathVariable Long id) {
	  try{
		  airportRepository.deleteById(id);
	    return ResponseEntity.ok("Airport has been deleted successfully!");      
	   }
	   catch (EmptyResultDataAccessException e){
	      return ResponseEntity.notFound().build();
	  }                                
	 }
	
	
}
