package com.example.miniprojetspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.miniprojetspring.entities.Flight;


public interface FlightRepository extends JpaRepository<Flight, Long> {

}
