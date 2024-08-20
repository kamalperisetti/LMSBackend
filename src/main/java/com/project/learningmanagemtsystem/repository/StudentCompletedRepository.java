package com.project.learningmanagemtsystem.repository;

import com.project.learningmanagemtsystem.model.StudentCompleted;

import java.util.List;


public interface StudentCompletedRepository {
    List<StudentCompleted> allStudentCompleted();
    StudentCompleted addStudentCompleted(StudentCompleted studentCompleted);
    StudentCompleted updateStudentCompleted(int studentCompletedId, StudentCompleted studentCompleted);
    void deleteStudentCompleted(int studentCompletedId);
    String updateStudentCompletedByCourseId(int courseId, StudentCompleted studentCompleted);
}

