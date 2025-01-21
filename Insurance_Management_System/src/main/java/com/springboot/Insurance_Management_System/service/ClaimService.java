package com.springboot.Insurance_Management_System.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.Insurance_Management_System.dao.ClaimDao;
import com.springboot.Insurance_Management_System.dao.InsurancePolicyDao;
import com.springboot.Insurance_Management_System.model.Claim;
import com.springboot.Insurance_Management_System.model.InsurancePolicy;
import com.springboot.Insurance_Management_System.model.ResponseStructure;
import com.springboot.Insurance_Management_System.exception.IdNotFoundException;

@Service
public class ClaimService {

    @Autowired
    private ClaimDao claimDao;

    @Autowired
    private InsurancePolicyDao insurancePolicyDao;

    @Autowired
    private ResponseStructure<Claim> responseStructure;

    @Autowired
    private ResponseStructure<List<Claim>> responseStructure2;

    private static int hitCounter = 1; // Counter for insuranceClaimNumber

    // Insert Claim
    public ResponseStructure<Claim> insertClaim(Claim claim, int policyId, MultipartFile file) {
        InsurancePolicy insurancePolicy = insurancePolicyDao.getByInsurancePolicyId(policyId);
        
        if (insurancePolicy != null) {
            claim.setInsurancePolicy(insurancePolicy);
            claim.setInsuranceClaimNumber("HIPCN-" + String.format("%06d", hitCounter++)); // Auto-increment

            // Handle file upload
            if (file != null && !file.isEmpty()) {
                String filePath = "C:\\Users\\krkir\\Downloads\\All Policy Claims\\" + file.getOriginalFilename();
                try {
                    file.transferTo(new File(filePath)); // Save file to the specified location
                    claim.setFilePath(filePath); // Save the file path in the claim
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            claimDao.insertClaim(claim);
            responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
            responseStructure.setMsg("Claimed successfully");
            responseStructure.setData(claim);
            return responseStructure;
        } else {
            responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
            responseStructure.setMsg("Not Claimed yet, please check again");
            responseStructure.setData(null);
            return responseStructure;
        }
    }

    // Get Claim by ID
    public ResponseStructure<Claim> getByClaimId(int claimId) {
        Claim claim = claimDao.getByClaimId(claimId);
        
        if (claim != null) {
            responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
            responseStructure.setMsg("This claim ID is present");
            responseStructure.setData(claim);
        } else {
            throw new IdNotFoundException("Given ID is not present in database");
        }
        return responseStructure;
    }

    // Get Insurance Claim Number by Claim ID
    public String getInsuranceClaimNumberById(int claimId) {
        Claim claim = claimDao.getByClaimId(claimId);
        if (claim != null) {
            return claim.getInsuranceClaimNumber();
        } else {
            throw new IdNotFoundException("Given ID is not present in database");
        }
    }

    // Get Insurance Claim Status by Insurance Claim Number
    public String getInsuranceClaimStatusByNumber(String insuranceClaimNumber) {
        Claim claim = claimDao.findByInsuranceClaimNumber(insuranceClaimNumber);
        if (claim != null) {
            return claim.getInsuranceClaimStatus();
        } else {
            throw new IdNotFoundException("Insurance Claim Number not found");
        }
    }

    // Delete Claim
    public ResponseStructure<Claim> deleteClaim(int claimId) {
        Claim claim = claimDao.deleteClaim(claimId);
        
        if (claim != null) {
            responseStructure.setStatusCode(HttpStatus.FOUND.value());
            responseStructure.setMsg("Claim deleted successfully");
            responseStructure.setData(claim);
        } else {
            throw new IdNotFoundException("Given ID is not present in database");
        }
        return responseStructure;
    }

    // Update Claim
    public ResponseStructure<Claim> updateClaim(Claim claim, int claimId, MultipartFile file) {
        Claim existingClaim = claimDao.getByClaimId(claimId);
        
        if (existingClaim != null) {
            existingClaim.setClaimNumber(claim.getClaimNumber());
            existingClaim.setClaimDate(claim.getClaimDate());
            existingClaim.setClaimDescription(claim.getClaimDescription());
            existingClaim.setClaimStatus(claim.getClaimStatus());
            
            // Update insurance claim status
            existingClaim.setInsuranceClaimStatus(claim.getInsuranceClaimStatus());

            // Handle file upload if new file is provided
            if (file != null && !file.isEmpty()) {
                String filePath = "C:\\Users\\krkir\\Downloads\\Admin Doccuments\\" + file.getOriginalFilename();
                try {
                    file.transferTo(new File(filePath)); // Save file to the specified location
                    existingClaim.setFilePath(filePath); // Save the file path in the existing claim
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            claimDao.updateClaim(existingClaim);
            responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
            responseStructure.setMsg("Claim updated successfully");
            responseStructure.setData(existingClaim);
        } else {
            throw new IdNotFoundException("Given ID is not present in database");
        }
        return responseStructure;
    }

    // Display All Claims
    public ResponseStructure<List<Claim>> displayAllClaim() {
        List<Claim> claims = claimDao.displayAllClaim();
        
        if (claims != null) {
            responseStructure2.setStatusCode(HttpStatus.FOUND.value());
            responseStructure2.setMsg("Claims - Details");
            responseStructure2.setData(claims);
            return responseStructure2;
        } else {
            responseStructure2.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseStructure2.setMsg("Claims details are not available");
            responseStructure2.setData(null);
            return responseStructure2;
        }
    }
}
