package com.springboot.Insurance_Management_System.service;

import com.springboot.Insurance_Management_System.model.Registration;
import com.springboot.Insurance_Management_System.repository.RegistrationRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public Registration saveRegistration(Registration registration) {
        return registrationRepository.save(registration);
    }

    public Registration getRegistrationByUserId(String userId) {
        return registrationRepository.findById(userId).orElse(null);
    }

    public void deleteRegistration(String userId) {
        registrationRepository.deleteById(userId);
    }

    public Registration updateRegistration(Registration registration) {
        return registrationRepository.save(registration);
    }

    public boolean authenticate(String userId, String password) {
        Optional<Registration> optionalRegistration = registrationRepository.findById(userId);
        return optionalRegistration
                .map(registration -> registration.getPassword().equals(password))
                .orElse(false);
    }
}
