package com.motohub.controller;

import com.motohub.model.Admin;
import com.motohub.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController   // 👈 cambiamos a RestController porque no tienes vistas todavía
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // ✅ Registrar administrador con validación de @motohub.com
    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        if (!admin.getEmail().endsWith("@motohub.com")) {
            return ResponseEntity.badRequest().body("El email debe terminar en @motohub.com");
        }
        Admin savedAdmin = adminService.registerAdmin(admin);
        return ResponseEntity.ok(savedAdmin);
    }

    // ✅ Login de administrador usando email y password
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin adminRequest) {
        return adminService.findByEmail(adminRequest.getEmail())
                .filter(admin -> admin.getPassword().equals(adminRequest.getPassword()))
                .map(admin -> ResponseEntity.ok("Login exitoso"))
                .orElse(ResponseEntity.status(401).body("Credenciales inválidas"));
    }

}
