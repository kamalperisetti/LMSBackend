package com.project.learningmanagemtsystem.repository;

import com.project.learningmanagemtsystem.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminJpaRepository extends JpaRepository<Admin, Integer> {
    Admin findByPhoneNumber(String phonenumber);
}
