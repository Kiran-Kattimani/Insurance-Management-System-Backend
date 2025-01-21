package com.springboot.Insurance_Management_System.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Claim {

    @Id
    @Column(name = "claimId", nullable = false)
    private int claimId;

    @Column(name = "claimNumber", nullable = false)
    private String claimNumber;

    @Column(name = "claimDescription", nullable = false)
    private String claimDescription;

    @Column(name = "claimDate", nullable = false)
    private String claimDate;

    @Column(name = "claimStatus", nullable = false)
    private String claimStatus;

    @Column(name = "filePath", nullable = true)
    private String filePath;

    @Column(name = "insuranceClaimNumber", nullable = false, unique = true)
    private String insuranceClaimNumber = "HIPCN-000001"; // Initialized value

    @Column(name = "insuranceClaimStatus", nullable = true)
    private String insuranceClaimStatus;

    @OneToOne
    @JoinColumn(name = "policyId", nullable = false)
    private InsurancePolicy insurancePolicy;

    // Getters and Setters
    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getClaimDescription() {
        return claimDescription;
    }

    public void setClaimDescription(String claimDescription) {
        this.claimDescription = claimDescription;
    }

    public String getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getInsuranceClaimNumber() {
        return insuranceClaimNumber;
    }

    public void setInsuranceClaimNumber(String insuranceClaimNumber) {
        this.insuranceClaimNumber = insuranceClaimNumber;
    }

    public String getInsuranceClaimStatus() {
        return insuranceClaimStatus;
    }

    public void setInsuranceClaimStatus(String insuranceClaimStatus) {
        this.insuranceClaimStatus = insuranceClaimStatus;
    }

    public InsurancePolicy getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }
}
