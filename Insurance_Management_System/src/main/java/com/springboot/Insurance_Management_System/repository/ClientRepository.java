package com.springboot.Insurance_Management_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.springboot.Insurance_Management_System.model.Client;

@Component
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
