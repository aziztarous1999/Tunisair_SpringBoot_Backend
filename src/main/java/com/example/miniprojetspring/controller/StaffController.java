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

import com.example.miniprojetspring.entities.Staff;
import com.example.miniprojetspring.exception.ResourceNotFoundException;
import com.example.miniprojetspring.repository.StaffRepository;

@RestController
@RequestMapping("/api/staff/")
@CrossOrigin(origins = "http://localhost:4200")
public class StaffController {

	@Autowired
	private StaffRepository staffRepository;
	

	//create staff
	@PostMapping("/create")
	public Staff createStaff(@Valid @RequestBody Staff staff) {
	    return staffRepository.save(staff);
	}
	
	//get all staffs 
	@GetMapping("/staffs")
	public List<Staff> getAllStaffs(){
		return staffRepository.findAll();
	}
	
  // Single item
  
  @GetMapping("/{id}")
  Staff getSingleStaff(@PathVariable Long id) {
    
    return staffRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("We couldn't found any staff with the specified id!"));
  }
	
  
  
  @PutMapping("/modifyStaff/{id}")
  Staff replaceStaff(@RequestBody Staff newstaff, @PathVariable Long id) {
    
    return staffRepository.findById(id)
      .map(staff -> {
    	  staff.setName(newstaff.getName());
    	  staff.setEmployeeID(newstaff.getEmployeeID());
    	  staff.setPosition(newstaff.getPosition());
        return staffRepository.save(staff);
      })
      .orElseGet(() -> {
    	  newstaff.setId(id);
        return staffRepository.save(newstaff);
      });
  }
  

  @DeleteMapping("/deleteStaff/{id}")
  public ResponseEntity<?> deleteStaff(@PathVariable Long id) {
	  try{
		  staffRepository.deleteById(id);
	    return ResponseEntity.ok("Staff has been deleted successfully!");      
	   }
	   catch (EmptyResultDataAccessException e){
	      return ResponseEntity.notFound().build();
	  }                                
	 }
  
	
}
