//package com.project.learningmanagemtsystem.controller;
//
//import com.project.learningmanagemtsystem.model.Students;
//import com.project.learningmanagemtsystem.service.StudentJpaService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class StudentJpaController {
//    @Autowired
//    public StudentJpaService studentJpaService;
//
//    @GetMapping("/student")
//    public List<Students> getAllStudents(){
//        return studentJpaService.getAllStudents();
//    }
//    @PostMapping("/student")
//    public Students addStudent(@RequestBody Students students){
//        return studentJpaService.addStudent(students);
//    }
//    @GetMapping("/student/{studentId}")
//    public Students getStudentById(@PathVariable("studentId") int studentId){
//        return studentJpaService.getStudentById(studentId);
//    }
//
//    @PutMapping("/student/{studentId}")
//    public Students updateStudent(@PathVariable("studentId") int studentId, @RequestBody Students students){
//        return studentJpaService.updateStudent(studentId, students);
//    }
//    @DeleteMapping("/student/{studentId}")
//    public void deleteStudent(@PathVariable("studentId") int studentId){
//        studentJpaService.deleteStudent(studentId);
//    }
//    @PostMapping("/student/login")
//    public Students findByPhoneNumber(@RequestBody Students students){
//        return studentJpaService.findByPhoneNumber(students);
//    }
//}
