package com.motohub.service;

import com.motohub.model.Admin;
import com.motohub.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin registerAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // 🔹 Ahora buscamos por email, no por username
    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
