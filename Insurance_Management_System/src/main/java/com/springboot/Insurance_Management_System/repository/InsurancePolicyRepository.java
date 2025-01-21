package com.springboot.Insurance_Management_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.Insurance_Management_System.model.InsurancePolicy;

@Repository
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Integer> {
    boolean existsByInsurancePolicyId(int insurancePolicyId);
    boolean existsByInsurancePolicyNumber(String insurancePolicyNumber);
}
