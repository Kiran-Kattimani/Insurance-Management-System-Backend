package com.springboot.Insurance_Management_System.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Insurance_Management_System.model.Admin;
import com.springboot.Insurance_Management_System.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long id, Admin adminDetails) {
        Admin admin = adminRepository.findById(id).orElseThrow();
        admin.setAdminName(adminDetails.getAdminName());
        admin.setAdminEmail(adminDetails.getAdminEmail());
        admin.setAdminPhone(adminDetails.getAdminPhone());
        admin.setAdminPassword(adminDetails.getAdminPassword());
        admin.setAdminConfirmPassword(adminDetails.getAdminConfirmPassword());
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}