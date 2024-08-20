package com.project.learningmanagemtsystem.repository;

import com.project.learningmanagemtsystem.model.CourseDetails;
import com.project.learningmanagemtsystem.model.Courses;

import java.util.List;

public interface CoursesRepository {
    List<Courses> getAllCourses();
    Courses addCourse(Courses courses);
    Courses updateCourse(int courseId, Courses courses);
    Courses getCourseById(int courseId);
    void deleteCourseById(int courseId);
    void deleteCourseByName(Courses courses);

//    List<Courses> findAllById(List<Integer> courseIds);
}
