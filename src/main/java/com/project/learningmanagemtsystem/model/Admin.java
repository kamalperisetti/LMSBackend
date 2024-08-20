package com.project.learningmanagemtsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int adminId;
    @Column(name = "admin_name", nullable = false)
    @NotNull(message = "Name Should Be Filled")
    private String adminName;
    @NotNull(message = "Phone Number Should Not Be Empty")
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;
    @NotNull(message = "Email Should Not Be Empty")
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "role", nullable = false)
    private String role = "admin";
    @NotNull(message = "Password Should Not Be Empty")
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "image_url",columnDefinition = "TEXT")
    private String imageUrl;

    public Admin(){}


    public Admin(int adminId, String adminName, String phoneNumber, String email, String role, String password, String imageUrl) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
        this.password = password;
        this.imageUrl = imageUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
