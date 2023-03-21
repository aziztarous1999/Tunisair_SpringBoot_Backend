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

import com.example.miniprojetspring.entities.RestaurationCompany;
import com.example.miniprojetspring.exception.ResourceNotFoundException;
import com.example.miniprojetspring.repository.RestaurationCompanyRepository;



@RestController
@RequestMapping("/api/restaurationcompany/")
public class RestaurationCompanyController {

	@Autowired
	private RestaurationCompanyRepository restaurationCompanyRepository;
	
	//create restauration companys
	@PostMapping("/create")
	public RestaurationCompany createRestaurationCompany(@Valid @RequestBody RestaurationCompany company) {
	    return restaurationCompanyRepository.save(company);
	}
	
	//get all restauration companys 
	@GetMapping("/restaurationcompanys")
	public List<RestaurationCompany> getAllRestaurationCompany(){
		return restaurationCompanyRepository.findAll();
	}
	
	// Single item
	  @GetMapping("/{id}")
	  RestaurationCompany getSingleRestaurationCompany(@PathVariable Long id) {
	    
	    return restaurationCompanyRepository.findById(id)
	      .orElseThrow(() -> new ResourceNotFoundException("We couldn't found any restauration company with the specified id!"));
	  }
	
	  @PutMapping("/modifyRestaurationcompanys/{id}")
	  RestaurationCompany replaceRestaurationCompany(@RequestBody RestaurationCompany newcompany, @PathVariable Long id) {
	    
	    return restaurationCompanyRepository.findById(id)
	      .map(company -> {
	    	  company.setfoodmenu(newcompany.getfoodmenu());
	    	  company.setName(newcompany.getName());
	        return restaurationCompanyRepository.save(company);
	      })
	      .orElseGet(() -> {
	    	  newcompany.setId(id);
	        return restaurationCompanyRepository.save(newcompany);
	      });
	  }
	
	  
	  
	  @DeleteMapping("/deleteRestaurationCompany/{id}")
	  public ResponseEntity<?> deleteRestaurationCompany(@PathVariable Long id) {
		  try{
			  restaurationCompanyRepository.deleteById(id);
		    return ResponseEntity.ok("Restauration company has been deleted successfully!");      
		   }
		   catch (EmptyResultDataAccessException e){
		      return ResponseEntity.notFound().build();
		  }                                
		 }
	
}
