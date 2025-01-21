package com.springboot.Insurance_Management_System.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.Insurance_Management_System.model.Claim;
import com.springboot.Insurance_Management_System.repository.ClaimRepository;

@Repository
public class ClaimDao {

    @Autowired
    private ClaimRepository claimRepository;

    // Insert Claim
    public Claim insertClaim(Claim claim) {
        return claimRepository.save(claim);    
    }

    // Get by Claim ID
    public Claim getByClaimId(int claimId) {
        Optional<Claim> optional = claimRepository.findById(claimId);
        return optional.orElse(null);
    }

    // Find by Insurance Claim Number
    public Claim findByInsuranceClaimNumber(String insuranceClaimNumber) {
        return claimRepository.findByInsuranceClaimNumber(insuranceClaimNumber);
    }

    // Delete Claim
    public Claim deleteClaim(int claimId) {
        Optional<Claim> optional = claimRepository.findById(claimId);
        if (optional.isPresent()) {
            Claim claim = optional.get();
            claimRepository.delete(claim);
            return claim;
        } else {
            return null;
        }
    }

    // Update Claim
    public Claim updateClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    // Display All Claims
    public List<Claim> displayAllClaim() {
        return claimRepository.findAll();
    }
}
