package com.project.learningmanagemtsystem.repository;

import com.project.learningmanagemtsystem.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoursesJpaRepository extends JpaRepository<Courses, Integer> {
    Optional<Courses> findByCourseName(String courseName);
//    void deleteByCourseName(Courses courses);
}
