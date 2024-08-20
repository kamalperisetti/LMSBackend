package com.project.learningmanagemtsystem.controller;


import com.project.learningmanagemtsystem.model.CourseDetails;
import com.project.learningmanagemtsystem.service.CourseDetailsJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseDetalisJapController {
    @Autowired
    public CourseDetailsJpaService courseDetailsJpaService;

    @GetMapping("/course_details")
    public List<CourseDetails> getAllCourseDetails(){
        return courseDetailsJpaService.getAllCourseDetails();
    }

    @PostMapping("/course_details")
    public CourseDetails addCourseDetails(@RequestBody CourseDetails courseDetails){
        return courseDetailsJpaService.addCourseDetails(courseDetails);
    }

    @GetMapping("/course_details/{course_details_id}")
    public CourseDetails getCourseDetailsById(@PathVariable("course_details_id")int courseDetailsId){
        return courseDetailsJpaService.getCourseDetailsById(courseDetailsId);
    }

    @PutMapping("/course_details/{course_details_id}")
    public CourseDetails updateCourseDetails(@PathVariable("course_details_id") int courseDetailsId, @RequestBody CourseDetails courseDetails){
        return courseDetailsJpaService.updateCourseDetails(courseDetailsId, courseDetails);
    }

    @DeleteMapping("/course_details/{course_details_id}")
    public void deleteCourseDetailsById(@PathVariable("course_details_id") int courseDetailsId){
        courseDetailsJpaService.deleteCourseDetailsById(courseDetailsId);
    }

    @GetMapping("/course_details/courseId/{courseId}")
    public List<CourseDetails> findCourseByCourseId(@PathVariable("courseId") int courseId){
        return courseDetailsJpaService.findCourseByCourseId(courseId);
    }
}
