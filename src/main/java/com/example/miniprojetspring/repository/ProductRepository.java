package com.example.miniprojetspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.miniprojetspring.entities.Product;

public interface ProductRepository  extends JpaRepository<Product, Long> {

}
