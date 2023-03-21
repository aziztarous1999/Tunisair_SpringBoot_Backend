package com.example.miniprojetspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.miniprojetspring.entities.Crew;

public interface CrewRepository  extends JpaRepository<Crew, Long> {

}
