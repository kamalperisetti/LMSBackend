package com.project.learningmanagemtsystem.repository;

import com.project.learningmanagemtsystem.model.CourseDetails;
import com.project.learningmanagemtsystem.model.Courses;

import java.util.List;
public interface CourseDetailsRepository {
    List<CourseDetails> getAllCourseDetails();
    CourseDetails getCourseDetailsById(int courserDetailsId);
    CourseDetails updateCourseDetails(int courseDetailsId, CourseDetails courseDetails);
    CourseDetails addCourseDetails(CourseDetails courseDetails);
    void deleteCourseDetailsById(int courserDetailsId);
    List<CourseDetails> findCourseByCourseId(int courseId);
}
