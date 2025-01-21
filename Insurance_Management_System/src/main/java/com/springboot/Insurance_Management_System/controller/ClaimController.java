package com.springboot.Insurance_Management_System.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.Insurance_Management_System.model.Claim;
import com.springboot.Insurance_Management_System.model.ResponseStructure;
import com.springboot.Insurance_Management_System.service.ClaimService;

@RestController
@RequestMapping("/api")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    // Insert Claim with file upload
    @PostMapping("/saveClaim/{policyId}")
    public ResponseStructure<Claim> insertClaim(
        @RequestParam("claim") String claimJson,
        @RequestParam("file") MultipartFile file,
        @PathVariable int policyId) throws IOException {
        
        Claim claim = new ObjectMapper().readValue(claimJson, Claim.class);
        return claimService.insertClaim(claim, policyId, file);
    }    

    // Get Claim by ID
    @GetMapping("/getByClaimId/{claimId}")
    public ResponseStructure<Claim> getByClaimId(@PathVariable int claimId) {
        return claimService.getByClaimId(claimId);
    }

    // Get Insurance Claim Number by Claim ID
    @GetMapping("/getInsuranceClaimNumber/{claimId}")
    public String getInsuranceClaimNumber(@PathVariable int claimId) {
        return claimService.getInsuranceClaimNumberById(claimId);
    }

    // Delete Claim
    @DeleteMapping("/deleteClaim/{claimId}")
    public ResponseStructure<Claim> deleteClaim(@PathVariable int claimId) {
        return claimService.deleteClaim(claimId);
    }   

    // Update Claim
    @PutMapping("/updateClaim/{claimId}")
    public ResponseStructure<Claim> updateClaim(
        @RequestParam("claim") String claimJson,
        @RequestParam("file") MultipartFile file,
        @PathVariable int claimId) throws IOException {
        
        Claim claim = new ObjectMapper().readValue(claimJson, Claim.class);
        
        // Call service method to update the claim
        return claimService.updateClaim(claim, claimId, file);
    }

    // Display All Claims
    @GetMapping("/displayAllClaim")
    public ResponseStructure<List<Claim>> displayAllClaim() {
        return claimService.displayAllClaim();
    }
    
    @GetMapping("/status/{insuranceClaimNumber}")
    public ResponseEntity<String> getClaimStatus(@PathVariable String insuranceClaimNumber) {
        String status = claimService.getInsuranceClaimStatusByNumber(insuranceClaimNumber);
        return ResponseEntity.ok(status);
    }
}
