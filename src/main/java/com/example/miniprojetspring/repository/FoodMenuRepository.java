package com.example.miniprojetspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.miniprojetspring.entities.FoodMenu;

public interface FoodMenuRepository extends JpaRepository<FoodMenu, Long> {

}
