package com.springboot.Insurance_Management_System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springboot.Insurance_Management_System.model.InsurancePolicy;
import com.springboot.Insurance_Management_System.model.ResponseStructure;
import com.springboot.Insurance_Management_System.service.InsurancePolicyService;

@RestController
@RequestMapping("/api")
public class InsurancePolicyController {

    @Autowired
    private InsurancePolicyService insurancePolicyService;

    // Insert insurancePolicy
    @PostMapping("/saveInsurancePolicy")
    public ResponseStructure<InsurancePolicy> insertInsurancePolicy(@RequestBody InsurancePolicy insurancePolicy) {
        return insurancePolicyService.insertInsurancePolicy(insurancePolicy);
    }    
    
    // Get by insurancePolicyId
    @GetMapping("/getByInsurancePolicyId/{insurancePolicyId}")
    public ResponseStructure<InsurancePolicy> getByInsurancePolicyId(@PathVariable int insurancePolicyId) {
        return insurancePolicyService.getByInsurancePolicyId(insurancePolicyId);
    }    
    
    // Update insurancePolicy
    @PutMapping("/updateInsurancePolicy/{insurancePolicyId}")
    public ResponseStructure<InsurancePolicy> updateInsurancePolicy(@RequestBody InsurancePolicy insurancePolicy, @PathVariable int insurancePolicyId) {
        return insurancePolicyService.updateInsurancePolicy(insurancePolicy, insurancePolicyId);
    }
    
    // Delete insurancePolicy
    @DeleteMapping("/deleteInsurancePolicy/{policyId}")
    public ResponseStructure<InsurancePolicy> deleteInsurancePolicy(@PathVariable int policyId) {
        return insurancePolicyService.deleteInsurancePolicy(policyId);
    }    
    
    // Display all insurance policies
    @GetMapping("/displayAllPolicy")
    public ResponseStructure<List<InsurancePolicy>> displayAllPolicy() {
        return insurancePolicyService.displayAllPolicy();
    }

    // Check if insurancePolicyId exists
    @GetMapping("/checkInsurancePolicyId/{insurancePolicyId}")
    public ResponseStructure<Boolean> checkInsurancePolicyId(@PathVariable int insurancePolicyId) {
        return insurancePolicyService.checkInsurancePolicyId(insurancePolicyId);
    }
    
    // Check if insurancePolicyNumber exists
    @GetMapping("/checkInsurancePolicyNumber/{insurancePolicyNumber}")
    public ResponseStructure<Boolean> checkInsurancePolicyNumber(@PathVariable String insurancePolicyNumber) {
        return insurancePolicyService.checkInsurancePolicyNumber(insurancePolicyNumber);
    }

    // Get insurancePolicyNumber by insurancePolicyId
    @GetMapping("/getInsurancePolicyNumber/{insurancePolicyId}")
    public ResponseStructure<String> getInsurancePolicyNumber(@PathVariable int insurancePolicyId) {
        return insurancePolicyService.getInsurancePolicyNumberById(insurancePolicyId);
    }
}
