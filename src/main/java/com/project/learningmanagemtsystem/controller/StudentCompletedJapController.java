package com.project.learningmanagemtsystem.controller;

import com.project.learningmanagemtsystem.model.StudentCompleted;
import com.project.learningmanagemtsystem.service.StudentCompletedJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentCompletedJapController {
    @Autowired
    public StudentCompletedJpaService studentCompletedJpaService;

    @GetMapping("/student-completed")
    public List<StudentCompleted> allStudentCompleted(){
        return studentCompletedJpaService.allStudentCompleted();
    }
    @PostMapping("/student-completed")
    public StudentCompleted addStudentCompleted(@RequestBody StudentCompleted studentCompleted){
        return studentCompletedJpaService.addStudentCompleted(studentCompleted);
    }
    @PutMapping("/student-completed/{studentCompletedId}")
    public StudentCompleted updateStudentCompleted(@PathVariable("studentCompletedId") int studentCompletedId, @RequestBody StudentCompleted studentCompleted){
        return studentCompletedJpaService.updateStudentCompleted(studentCompletedId, studentCompleted);
    }
    @DeleteMapping("/student-completed/{studentCompletedId}")
    public void deleteStudentCompleted(@PathVariable("studentCompletedId") int studentCompletedId){
        studentCompletedJpaService.deleteStudentCompleted(studentCompletedId);
    }
    @PutMapping("/student-completed/courseId/{courseId}")
    public String updateStudentCompletedByCourseId(@PathVariable("courseId") int courseId, @RequestBody StudentCompleted studentCompleted){
        return  studentCompletedJpaService.updateStudentCompletedByCourseId(courseId, studentCompleted);
    }
}
