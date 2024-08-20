package com.project.learningmanagemtsystem.service;

import com.project.learningmanagemtsystem.model.Admin;
//import com.project.learningmanagemtsystem.model.Students;
import com.project.learningmanagemtsystem.repository.AdminJpaRepository;
import com.project.learningmanagemtsystem.repository.AdminRepository;
import com.project.learningmanagemtsystem.validations.ValidationImplementaion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AdminJpaService implements AdminRepository {
    @Autowired
    private AdminJpaRepository adminJpaRepository;
    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> admins = adminJpaRepository.findAll();
        if(admins.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admins List Is Empty");
        }else{
            return admins;
        }

    }

    @Override
    public Admin addAdmin(Admin admin) {
        if(ValidationImplementaion.mobileNumberValidation(admin.getPhoneNumber())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Provide a Valid Phone Number");
        }
        if(admin.getPassword() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Provide Password");
        }
        if(ValidationImplementaion.emailValidation(admin.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Provide a Valid Email ID");
        }
        adminJpaRepository.save(admin);
        return admin;
    }

    @Override
    public Admin updateAdmin(int adminId, Admin admin) {
        try{
            Admin existingAdmin = adminJpaRepository.findById(adminId).get();
            if(admin.getAdminName() != null){
                existingAdmin.setAdminName(admin.getAdminName());
            }
            if(admin.getEmail() != null){
                if(ValidationImplementaion.emailValidation(admin.getEmail())){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Provide a Valid Email ID");
                }
                existingAdmin.setEmail(admin.getEmail());
            }
            if(admin.getPhoneNumber() != null){
                if(ValidationImplementaion.mobileNumberValidation(admin.getPhoneNumber())){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Provide a Valid Phone Number");
                }
                existingAdmin.setPhoneNumber(admin.getPhoneNumber());
            }
            adminJpaRepository.save(existingAdmin);
            return existingAdmin;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin Not Found With This ID");
        }

    }

    @Override
    public Admin getAdminById(int adminId) {
        try{
            Admin admin = adminJpaRepository.findById(adminId).get();
            return admin;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin Not Found With This ID");
        }
    }

    @Override
    public Admin getAdminByPhoneNumber(Admin admin){

        Admin existingAdmin =  adminJpaRepository.findByPhoneNumber(admin.getPhoneNumber());

        if(existingAdmin == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found With This Number");
        } else if (!existingAdmin.getPassword().equals(admin.getPassword())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Password doesn't match please Provide valid password");
        } else{
            return existingAdmin;
        }
    }

    @Override
    public void deleteAdminById(int adminId) {
        Optional<Admin> admin = adminJpaRepository.findById(adminId);
        if(admin.isPresent()){
            adminJpaRepository.deleteById(adminId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Admin Deleted Successfully");
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin Not Found With This ID");
        }
    }
}
