package com.example.miniprojetspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.miniprojetspring.entities.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {

}
