package com.example.miniprojetspring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "crew")
public class Crew {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "crew_name")
	private String name;
	@Column(name = "employeeID")
	private String employeeID;
	@Column(name = "yearsOfExperience")
	private Integer yearsOfExperience;
	@Column(name = "role")
	private Crew_enum role;
	
	public Crew() {
		
	}

	public Crew(String name, String employeeID, Integer yearsOfExperience, Crew_enum role) {
		super();
		this.name = name;
		this.employeeID = employeeID;
		this.yearsOfExperience = yearsOfExperience;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public Crew_enum getRole() {
		return role;
	}

	public void setRole(Crew_enum role) {
		this.role = role;
	}
	
	
	
	
	
}
