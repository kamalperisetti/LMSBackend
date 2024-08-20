package com.project.learningmanagemtsystem.controller;

import com.project.learningmanagemtsystem.model.Courses;
import com.project.learningmanagemtsystem.service.CoursesJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseJpaController {
    @Autowired
    public CoursesJpaService coursesJpaService;
//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/courses")
    public List<Courses> getAllCourses(){
        return coursesJpaService.getAllCourses();
    }

    @PostMapping("/courses")
    public Courses addCourse(@RequestBody Courses courses){
        return coursesJpaService.addCourse(courses);
    }
//    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/courses/{courseId}")
    public Courses updateCourse(@PathVariable("courseId") int courseId, @RequestBody Courses courses){
        return  coursesJpaService.updateCourse(courseId, courses);
    }

    @GetMapping("/courses/{courseId}")
    public Courses getCourseById(@PathVariable("courseId") int courseId){
        return coursesJpaService.getCourseById(courseId);
    }

    @DeleteMapping("/courses/{courseId}")
    public void deleteCourseById(@PathVariable("courseId") int courseId){
        coursesJpaService.deleteCourseById(courseId);
    }

    @PostMapping("/courses/courseName")
    public void deleteCourseByName(@RequestBody Courses courses){
         coursesJpaService.deleteCourseByName(courses);
    }
}
