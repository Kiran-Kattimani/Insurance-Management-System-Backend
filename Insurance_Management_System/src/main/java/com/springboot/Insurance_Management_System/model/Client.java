package com.springboot.Insurance_Management_System.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Client {

    @Id
    @Column(name = "clientId", nullable = false)
    private int clientId;

    @Column(name = "clientName", nullable = false)
    private String clientName;

    @Column(name = "clientDateOfBirth", nullable = false)
    private String clientDateOfBirth;

    @Column(name = "clientAddress", nullable = false)
    private String clientAddress;

    @Column(name = "clientContactInformation", nullable = false)
    private long clientContactInformation;

    @ManyToOne
    @JoinColumn(name = "policyId", nullable = false)
    private InsurancePolicy insurancePolicy;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientDateOfBirth() {
        return clientDateOfBirth;
    }

    public void setClientDateOfBirth(String clientDateOfBirth) {
        this.clientDateOfBirth = clientDateOfBirth;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public long getClientContactInformation() {
        return clientContactInformation;
    }

    public void setClientContactInformation(long clientContactInformation) {
        this.clientContactInformation = clientContactInformation;
    }

    public InsurancePolicy getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }
}
