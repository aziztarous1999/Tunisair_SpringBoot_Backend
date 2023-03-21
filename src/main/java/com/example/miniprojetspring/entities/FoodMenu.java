package com.example.miniprojetspring.entities;


import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "foodmenu")
public class FoodMenu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "foodmenu_name")
	private String name;
	
	
	  @ManyToMany
	  @JoinTable(name = "dishes",
	 joinColumns = @JoinColumn(name = "foodmenu_id"),
	 inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private List<Dish> dishes = new ArrayList<>();
	
	
	public FoodMenu() {
		
	}


	public FoodMenu(String name, List<Dish> dishes) {
		super();
		this.name = name;
		this.dishes = dishes;
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


	public List<Dish> getDishes() {
		return dishes;
	}


	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}


	
	

}
