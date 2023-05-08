package com.example.miniprojetspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.miniprojetspring.entities.Dashboard;
import com.example.miniprojetspring.repository.AirportRepository;
import com.example.miniprojetspring.repository.CrewRepository;
import com.example.miniprojetspring.repository.DishRepository;
import com.example.miniprojetspring.repository.StaffRepository;

@RestController
@RequestMapping("/api/dashboard/")
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardController {

	@Autowired
	private DishRepository dishRepo;
	@Autowired
	private StaffRepository staffRepo;
	@Autowired
	private CrewRepository crewRepo;
	@Autowired
	private AirportRepository airportRepository;
	
	
	
	
	  @GetMapping("/count")
	  Dashboard getCount() {
	    return new Dashboard(dishRepo.count(), staffRepo.count(), crewRepo.count(), airportRepository.count());
	  }
	
	
}
