package com.project.learningmanagemtsystem.repository;

import com.project.learningmanagemtsystem.model.CourseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDetailsJpaRepository extends JpaRepository<CourseDetails, Integer> {
    List<CourseDetails> findByCourseId(int courseId);
}
