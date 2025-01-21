package com.springboot.Insurance_Management_System.controller;

import com.springboot.Insurance_Management_System.model.Registration;
import com.springboot.Insurance_Management_System.service.RegistrationService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<Registration> createRegistration(@RequestBody Registration registration) {
        return ResponseEntity.ok(registrationService.saveRegistration(registration));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Registration> getRegistrationByUserId(@PathVariable String userId) {
        Registration registration = registrationService.getRegistrationByUserId(userId);
        return registration != null ? ResponseEntity.ok(registration) : ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Registration> updateRegistration(@RequestBody Registration registration) {
        return ResponseEntity.ok(registrationService.updateRegistration(registration));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable String userId) {
        registrationService.deleteRegistration(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String userId, @RequestParam String password) {
        boolean isAuthenticated = registrationService.authenticate(userId, password);
        Map<String, String> response = new HashMap<>();
        if (isAuthenticated) {
            String policyId = "some_policy_id"; // Replace with actual policy ID
            response.put("policyId", policyId);
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(401).body(response);
        }
    }
}
