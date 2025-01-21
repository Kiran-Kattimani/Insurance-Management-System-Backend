package com.springboot.Insurance_Management_System.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.Insurance_Management_System.dao.InsurancePolicyDao;
import com.springboot.Insurance_Management_System.model.InsurancePolicy;
import com.springboot.Insurance_Management_System.model.ResponseStructure;

@Service
public class InsurancePolicyService {

    @Autowired
    private InsurancePolicyDao insurancePolicyDao;

    @Autowired
    private ResponseStructure<InsurancePolicy> responseStructureInsurancePolicy;

    @Autowired
    private ResponseStructure<List<InsurancePolicy>> responseStructurePolicyList;

    @Autowired
    private ResponseStructure<Boolean> responseStructureBoolean;

    @Autowired
    private ResponseStructure<String> responseStructureString;

    // Insert insurancePolicy
    public ResponseStructure<InsurancePolicy> insertInsurancePolicy(InsurancePolicy insurancePolicy) {
        InsurancePolicy savedPolicy = insurancePolicyDao.insertInsurancePolicy(insurancePolicy);
        
        if (savedPolicy != null) {
            responseStructureInsurancePolicy.setStatusCode(HttpStatus.ACCEPTED.value());
            responseStructureInsurancePolicy.setMsg("Data inserted successfully");
            responseStructureInsurancePolicy.setData(savedPolicy);
            return responseStructureInsurancePolicy;
        } else {
            responseStructureInsurancePolicy.setStatusCode(HttpStatus.BAD_REQUEST.value());
            responseStructureInsurancePolicy.setMsg("Data not inserted, please check again your code");
            responseStructureInsurancePolicy.setData(null);
            return responseStructureInsurancePolicy;
        }
    }
    
    // Get by insurancePolicyId
    public ResponseStructure<InsurancePolicy> getByInsurancePolicyId(int insurancePolicyId) {
        InsurancePolicy insurancePolicy = insurancePolicyDao.getByInsurancePolicyId(insurancePolicyId);
        
        if (insurancePolicy != null) {
            responseStructureInsurancePolicy.setStatusCode(HttpStatus.FOUND.value());
            responseStructureInsurancePolicy.setMsg("Data fetch successful, data is available");
            responseStructureInsurancePolicy.setData(insurancePolicy);
            return responseStructureInsurancePolicy;
        } else {
            responseStructureInsurancePolicy.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseStructureInsurancePolicy.setMsg("Please check your ID which you have given");
            responseStructureInsurancePolicy.setData(null);
            return responseStructureInsurancePolicy;
        }
    }
    
    // Update insurancePolicy
    public ResponseStructure<InsurancePolicy> updateInsurancePolicy(InsurancePolicy insurancePolicy, int insurancePolicyId) {
        InsurancePolicy updatedPolicy = insurancePolicyDao.updateInsurancePolicy(insurancePolicy, insurancePolicyId);
        
        if (updatedPolicy != null) {
            responseStructureInsurancePolicy.setStatusCode(HttpStatus.ACCEPTED.value());
            responseStructureInsurancePolicy.setMsg("Data is updated successfully because ID is present");
            responseStructureInsurancePolicy.setData(updatedPolicy);
            return responseStructureInsurancePolicy;
        } else {
            responseStructureInsurancePolicy.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
            responseStructureInsurancePolicy.setMsg("Given ID is not present in database");
            responseStructureInsurancePolicy.setData(null);
            return responseStructureInsurancePolicy;
        }
    }
    
    // Delete insurancePolicy
    public ResponseStructure<InsurancePolicy> deleteInsurancePolicy(int policyId) {
        InsurancePolicy deletedPolicy = insurancePolicyDao.deleteInsurancePolicy(policyId);
        
        if (deletedPolicy != null) {
            responseStructureInsurancePolicy.setStatusCode(HttpStatus.FOUND.value());
            responseStructureInsurancePolicy.setMsg("Data Deleted Successfully");
            responseStructureInsurancePolicy.setData(deletedPolicy);
            return responseStructureInsurancePolicy;
        } else {
            responseStructureInsurancePolicy.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseStructureInsurancePolicy.setMsg("Data is not deleted because ID is not present in database");
            responseStructureInsurancePolicy.setData(null);
            return responseStructureInsurancePolicy;
        }
    }
    
    // Display all insurance policies
    public ResponseStructure<List<InsurancePolicy>> displayAllPolicy() {
        List<InsurancePolicy> insurancePolicies = insurancePolicyDao.displayAllPolicy();
        
        if (!insurancePolicies.isEmpty()) {
            responseStructurePolicyList.setStatusCode(HttpStatus.FOUND.value());
            responseStructurePolicyList.setMsg("Data is present");
            responseStructurePolicyList.setData(insurancePolicies);
            return responseStructurePolicyList;
        } else {
            responseStructurePolicyList.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseStructurePolicyList.setMsg("Data is not present in the database");
            responseStructurePolicyList.setData(null);
            return responseStructurePolicyList;
        }
    }

    // Check if insurancePolicyId exists
    public ResponseStructure<Boolean> checkInsurancePolicyId(int insurancePolicyId) {
        boolean exists = insurancePolicyDao.checkInsurancePolicyId(insurancePolicyId);
        
        responseStructureBoolean.setStatusCode(exists ? HttpStatus.OK.value() : HttpStatus.NOT_FOUND.value());
        responseStructureBoolean.setMsg(exists ? "Insurance Policy ID exists" : "Insurance Policy ID does not exist");
        responseStructureBoolean.setData(exists);
        return responseStructureBoolean;
    }
    
    // Check if insurancePolicyNumber exists
    public ResponseStructure<Boolean> checkInsurancePolicyNumber(String insurancePolicyNumber) {
        boolean exists = insurancePolicyDao.checkInsurancePolicyNumber(insurancePolicyNumber);
        
        responseStructureBoolean.setStatusCode(exists ? HttpStatus.OK.value() : HttpStatus.NOT_FOUND.value());
        responseStructureBoolean.setMsg(exists ? "Insurance Policy Number exists" : "Insurance Policy Number does not exist");
        responseStructureBoolean.setData(exists);
        return responseStructureBoolean;
    }

    // Get insurancePolicyNumber by insurancePolicyId
    public ResponseStructure<String> getInsurancePolicyNumberById(int insurancePolicyId) {
        InsurancePolicy insurancePolicy = insurancePolicyDao.getByInsurancePolicyId(insurancePolicyId);
        
        if (insurancePolicy != null && insurancePolicy.getInsurancePolicyNumber() != null) {
            responseStructureString.setStatusCode(HttpStatus.OK.value());
            responseStructureString.setMsg("Insurance Policy Number retrieved successfully");
            responseStructureString.setData(insurancePolicy.getInsurancePolicyNumber());
            return responseStructureString;
        } else {
            responseStructureString.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseStructureString.setMsg("Insurance Policy Number not found for the given ID");
            responseStructureString.setData(null);
            return responseStructureString;
        }
    }
}
