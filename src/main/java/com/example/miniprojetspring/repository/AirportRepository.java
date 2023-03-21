package com.example.miniprojetspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.miniprojetspring.entities.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {

}
