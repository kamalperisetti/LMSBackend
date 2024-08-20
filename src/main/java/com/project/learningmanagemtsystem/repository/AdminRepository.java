package com.project.learningmanagemtsystem.repository;

import com.project.learningmanagemtsystem.model.Admin;

import java.util.List;

public interface AdminRepository {
    List<Admin> getAllAdmins();
    Admin addAdmin(Admin admin);
    Admin updateAdmin(int adminId, Admin admin);
    Admin getAdminById(int adminId);
    void deleteAdminById(int adminId);
    Admin getAdminByPhoneNumber(Admin admin);
}
