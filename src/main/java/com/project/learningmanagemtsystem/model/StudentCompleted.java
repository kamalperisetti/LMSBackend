package com.project.learningmanagemtsystem.model;

import jakarta.persistence.*;
@Entity
@Table(name = "student_completed")
public class StudentCompleted {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int studentCompletedId;
    @Column(name = "student_id")
    private int studentId;
    @Column(name = "course_id")
    private int courseId;
    @Column(name = "total_content")
    private int totalContent;
    @Column(name = "completed")
    private int completed;

    public StudentCompleted(){}

    public StudentCompleted(int studentCompletedId, int studentId, int courseId, int totalContent, int completed) {
        this.studentCompletedId = studentCompletedId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.totalContent = totalContent;
        this.completed = completed;
    }

    public int getStudentCompletedId() {
        return studentCompletedId;
    }

    public void setStudentCompletedId(int studentCompletedId) {
        this.studentCompletedId = studentCompletedId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTotalContent() {
        return totalContent;
    }

    public void setTotalContent(int totalContent) {
        this.totalContent = totalContent;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }
}
