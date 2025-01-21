package com.springboot.Insurance_Management_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.Insurance_Management_System.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}