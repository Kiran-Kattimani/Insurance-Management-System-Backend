package com.springboot.Insurance_Management_System.repository;

import com.springboot.Insurance_Management_System.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, String> {
    // Custom query methods (if any) can be added here
}
