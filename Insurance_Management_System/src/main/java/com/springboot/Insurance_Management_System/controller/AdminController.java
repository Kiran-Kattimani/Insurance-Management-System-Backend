package com.springboot.Insurance_Management_System.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Insurance_Management_System.model.Admin;
import com.springboot.Insurance_Management_System.model.Client;
import com.springboot.Insurance_Management_System.model.InsurancePolicy;
import com.springboot.Insurance_Management_System.model.Claim; // Import Claim model
import com.springboot.Insurance_Management_System.model.ResponseStructure;
import com.springboot.Insurance_Management_System.service.AdminService;
import com.springboot.Insurance_Management_System.service.ClientService;
import com.springboot.Insurance_Management_System.service.InsurancePolicyService;
import com.springboot.Insurance_Management_System.service.ClaimService; // Import ClaimService

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private InsurancePolicyService insurancePolicyService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClaimService claimService; // Injecting ClaimService

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin adminDetails) {
        Admin updatedAdmin = adminService.updateAdmin(id, adminDetails);
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/displayAllPolicy")
    public ResponseStructure<List<InsurancePolicy>> displayAllPolicy() {
        return insurancePolicyService.displayAllPolicy();
    }

    @GetMapping("/displayAllClient")
    public ResponseStructure<List<Client>> displayAllClient() {
        return clientService.displayAllCLient();
    }

    @GetMapping("/displayAllClaim") // New endpoint to display all claims
    public ResponseStructure<List<Claim>> displayAllClaim() {
        return claimService.displayAllClaim();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam Long adminId, @RequestParam String adminPassword) {
        Optional<Admin> adminOptional = adminService.getAdminById(adminId);

        Map<String, String> response = new HashMap<>();
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            if (admin.getAdminPassword().equals(adminPassword)) {
                response.put("message", "Login Success!");
                return ResponseEntity.ok(response);
            }
        }

        response.put("message", "Invalid Credentials!!!");
        return ResponseEntity.status(401).body(response);
    }
}
