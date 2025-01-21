package com.springboot.Insurance_Management_System.model;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @NotNull // Ensure userId cannot be null
    private String userId;

    @Column(nullable = false) // Ensure name cannot be null or empty
    private String name;

    @Column(nullable = false) // Ensure email cannot be null or empty
    private String email;

    @Column(nullable = false) // Ensure phone cannot be null or empty
    private String phone;

    @Column(nullable = false) // Ensure password cannot be null or empty
    private String password;

    @Column(nullable = false) // Ensure confirmPassword cannot be null or empty
    private String confirmPassword;

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
