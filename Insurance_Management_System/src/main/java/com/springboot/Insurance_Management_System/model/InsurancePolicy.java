package com.springboot.Insurance_Management_System.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "insurancePolicy")
public class InsurancePolicy {

    @Id
    @Column(name = "policyId", nullable = false)
    private int insurancePolicyId;

    @Column(name = "policyNumber", nullable = false, unique = true)
    private String insurancePolicyNumber; // Keeping this as String to retain "HIP" prefix

    @Column(name = "policyType", nullable = false)
    private String insurancePolicyType;

    @Column(name = "policyCoverageAmount", nullable = false)
    private long insurancePolicyCoverageAmount;

    @Column(name = "policyPremium", nullable = false)
    private String insurancePolicyPremium;

    @Column(name = "policyStartDate", nullable = false)
    private String insurancePolicyStartDate;

    @Column(name = "policyEndDate", nullable = false)
    private String insurancePolicyEndDate;

    @OneToMany(mappedBy = "insurancePolicy", cascade = CascadeType.ALL)
    private List<Client> clients;

    @OneToOne(mappedBy = "insurancePolicy")
    private Claim claim;

    // Getters and setters
    public int getInsurancePolicyId() {
        return insurancePolicyId;
    }

    public void setInsurancePolicyId(int insurancePolicyId) {
        this.insurancePolicyId = insurancePolicyId;
    }

    public String getInsurancePolicyNumber() {
        return insurancePolicyNumber;
    }

    public void setInsurancePolicyNumber(String insurancePolicyNumber) {
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    public String getInsurancePolicyType() {
        return insurancePolicyType;
    }

    public void setInsurancePolicyType(String insurancePolicyType) {
        this.insurancePolicyType = insurancePolicyType;
    }

    public long getInsurancePolicyCoverageAmount() {
        return insurancePolicyCoverageAmount;
    }

    public void setInsurancePolicyCoverageAmount(long insurancePolicyCoverageAmount) {
        this.insurancePolicyCoverageAmount = insurancePolicyCoverageAmount;
    }

    public String getInsurancePolicyPremium() {
        return insurancePolicyPremium;
    }

    public void setInsurancePolicyPremium(String insurancePolicyPremium) {
        this.insurancePolicyPremium = insurancePolicyPremium;
    }

    public String getInsurancePolicyStartDate() {
        return insurancePolicyStartDate;
    }

    public void setInsurancePolicyStartDate(String insurancePolicyStartDate) {
        this.insurancePolicyStartDate = insurancePolicyStartDate;
    }

    public String getInsurancePolicyEndDate() {
        return insurancePolicyEndDate;
    }

    public void setInsurancePolicyEndDate(String insurancePolicyEndDate) {
        this.insurancePolicyEndDate = insurancePolicyEndDate;
    }

    // Uncomment the following methods if you need to use them
    /*
    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }
    */
}
