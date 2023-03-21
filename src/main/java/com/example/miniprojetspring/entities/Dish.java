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
@Table(name = "dish")
public class Dish {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "dish_name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "price")
	private long price;
	
	@OneToMany
	private List<FoodMenu> foodmenu = new ArrayList<>();
	
	public Dish() {
		
	}

	public Dish(String name, String description, long price, List<FoodMenu> foodmenu) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public List<FoodMenu> getFoodMenus() {
		return foodmenu;
	}

	public void setFoodMenus(List<FoodMenu> foodmenu) {
		this.foodmenu = foodmenu;
	}
	
	
	
	

}
