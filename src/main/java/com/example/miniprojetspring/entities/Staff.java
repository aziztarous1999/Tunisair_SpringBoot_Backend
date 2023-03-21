package com.example.miniprojetspring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "staff")
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "staff_name")
	private String name;
	@Column(name = "employeeID")
	private String employeeID;
	@Column(name = "position")
	private String position;
	
	
public Staff() {
		
	}


public Staff(String name, String employeeID, String position) {
	super();
	this.name = name;
	this.employeeID = employeeID;
	this.position = position;
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


public String getPosition() {
	return position;
}


public void setPosition(String position) {
	this.position = position;
}


public long getId() {
	return id;
}


public void setId(long id) {
	this.id = id;
}
	
}
