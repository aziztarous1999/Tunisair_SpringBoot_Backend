package com.example.miniprojetspring.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurationcompany")
public class RestaurationCompany {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "company_name")
	private String name;
	@OneToMany
  private List<FoodMenu> foodmenu = new ArrayList<>();
	@OneToMany
	private List<Flight> flighs = new ArrayList<>();
	
public RestaurationCompany() {
		
	}

public RestaurationCompany(String name, List<FoodMenu> foodmenu) {
	super();
	this.name = name;
	this.foodmenu = foodmenu;
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

public List<FoodMenu> getfoodmenu() {
	return foodmenu;
}

public void setfoodmenu(List<FoodMenu> foodmenu) {
	this.foodmenu = foodmenu;
}


	
	
}
