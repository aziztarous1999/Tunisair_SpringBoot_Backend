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

import com.example.miniprojetspring.entities.Flight;
import com.example.miniprojetspring.exception.ResourceNotFoundException;
import com.example.miniprojetspring.repository.FlightRepository;

@RestController
@RequestMapping("/api/flight/")
@CrossOrigin(origins = "http://localhost:4200")
public class FlightController {

	@Autowired
	private FlightRepository flightRepository;
	
	

	//create flight
	@PostMapping("/create")
	public Flight createFlight(@Valid @RequestBody Flight flight) {
	    return flightRepository.save(flight);
	}
	
	//get all flights 
	@GetMapping("/flights")
	public List<Flight> getAllFlight(){
		return flightRepository.findAll();
	}
	
  // Single item
  @GetMapping("/{id}")
  Flight getSingleFlight(@PathVariable Long id) {
    return flightRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("We couldn't found any flight with the specified id!"));
  }
	
	
  
  @PutMapping("/modifyFlight/{id}")
  Flight replaceFlight(@RequestBody Flight newflight, @PathVariable Long id) {
    
    return flightRepository.findById(id)
      .map(flight -> {
    	  flight.setArrivalTime(newflight.getArrivalTime());
    	  flight.setClasse(newflight.getClasse());
    	  flight.setCo_pilot(newflight.getCo_pilot());
    	  flight.setDelayTime(newflight.getDelayTime());
    	  flight.setDepartureTime(newflight.getDepartureTime());
    	  flight.setDestination(newflight.getDestination());
    	  flight.setFlightNumber(newflight.getFlightNumber());
    	  flight.setGate(newflight.getGate());
    	  flight.setPilot(newflight.getPilot());
    	  flight.setRestaurationCompany(newflight.getRestaurationCompany());
    	  flight.setSeatsNumber(newflight.getSeatsNumber());
    	  flight.setStatus(newflight.getStatus());
        return flightRepository.save(flight);
      })
      .orElseGet(() -> {
    	  newflight.setId(id);
        return flightRepository.save(newflight);
      });
  }
	
  @DeleteMapping("/deleteFlight/{id}")
  public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
	  try{
		  flightRepository.deleteById(id);
	    return ResponseEntity.ok("Flight has been deleted successfully!");      
	   }
	   catch (EmptyResultDataAccessException e){
	      return ResponseEntity.notFound().build();
	  }                                
	 }
	
}
