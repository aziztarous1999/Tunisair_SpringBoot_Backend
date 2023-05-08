package com.example.miniprojetspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.miniprojetspring.entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
}
