package com.project.learningmanagemtsystem.repository;

import com.project.learningmanagemtsystem.model.StudentCompleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentCompletedJpaRepository extends JpaRepository<StudentCompleted, Integer> {
    List<StudentCompleted> findByStudentId(int studentId);
    List<StudentCompleted> findByCourseId(int courseId);
}
