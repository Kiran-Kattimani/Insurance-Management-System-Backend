package com.springboot.Insurance_Management_System.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.springboot.Insurance_Management_System.model.InsurancePolicy;
import com.springboot.Insurance_Management_System.repository.InsurancePolicyRepository;

import java.util.List;

@Repository
public class InsurancePolicyDao {

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    public InsurancePolicy insertInsurancePolicy(InsurancePolicy insurancePolicy) {
        // Generate policy number
        String generatedPolicyNumber = generatePolicyNumber();
        insurancePolicy.setInsurancePolicyNumber(generatedPolicyNumber);
        return insurancePolicyRepository.save(insurancePolicy);
    }

    // Method to generate the next policy number
    private String generatePolicyNumber() {
        List<InsurancePolicy> policies = insurancePolicyRepository.findAll();
        int maxNumber = 0;

        // Extract the max number from existing policy numbers
        for (InsurancePolicy policy : policies) {
            String policyNumber = policy.getInsurancePolicyNumber();
            if (policyNumber.startsWith("HIP")) {
                int number = Integer.parseInt(policyNumber.substring(3)); // Get the numeric part
                if (number > maxNumber) {
                    maxNumber = number;
                }
            }
        }

        return "HIP" + String.format("%06d", maxNumber + 1); // Increment and format
    }

    public InsurancePolicy getByInsurancePolicyId(int insurancePolicyId) {
        return insurancePolicyRepository.findById(insurancePolicyId).orElse(null);
    }

    public InsurancePolicy updateInsurancePolicy(InsurancePolicy insurancePolicy, int insurancePolicyId) {
        if (insurancePolicyRepository.existsByInsurancePolicyId(insurancePolicyId)) {
            insurancePolicy.setInsurancePolicyId(insurancePolicyId);
            return insurancePolicyRepository.save(insurancePolicy);
        }
        return null;
    }

    public InsurancePolicy deleteInsurancePolicy(int policyId) {
        InsurancePolicy insurancePolicy = getByInsurancePolicyId(policyId);
        if (insurancePolicy != null) {
            insurancePolicyRepository.delete(insurancePolicy);
        }
        return insurancePolicy;
    }

    public List<InsurancePolicy> displayAllPolicy() {
        return insurancePolicyRepository.findAll();
    }

    public boolean checkInsurancePolicyId(int insurancePolicyId) {
        return insurancePolicyRepository.existsByInsurancePolicyId(insurancePolicyId);
    }

    public boolean checkInsurancePolicyNumber(String insurancePolicyNumber) {
        return insurancePolicyRepository.existsByInsurancePolicyNumber(insurancePolicyNumber);
    }
}
