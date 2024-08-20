package com.project.learningmanagemtsystem.controller;

import com.project.learningmanagemtsystem.model.Admin;
import com.project.learningmanagemtsystem.service.AdminJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminJpaController {
    @Autowired
    public AdminJpaService adminJpaService;

    @GetMapping("/admin")
    public List<Admin> getAllAdmins(){
        return adminJpaService.getAllAdmins();
    }

    @PostMapping("/admin/register")
    public Admin addAdmin(@RequestBody Admin admin){
        return adminJpaService.addAdmin(admin);
    }

    @GetMapping("/admin/{adminId}")
    public Admin getAdminById(@PathVariable("adminId") int adminId){
        return adminJpaService.getAdminById(adminId);
    }

    @PutMapping("/admin/{adminId}")
    public Admin updateAdmin(@PathVariable("adminId") int adminId,@RequestBody Admin admin){
        return adminJpaService.updateAdmin(adminId, admin);
    }

    @DeleteMapping("/admin/{adminId}")
    public void deleteAdminById(@PathVariable("adminId") int adminId, @RequestBody Admin admin){
        adminJpaService.deleteAdminById(adminId);
    }

    @PostMapping("/admin/login")
    public Admin getAdminByPhoneNumber(@RequestBody Admin admin){
        return adminJpaService.getAdminByPhoneNumber(admin);
    }

}
