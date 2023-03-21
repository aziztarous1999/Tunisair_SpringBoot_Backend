package com.example.miniprojetspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.miniprojetspring.entities.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {

}
