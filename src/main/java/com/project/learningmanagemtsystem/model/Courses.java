package com.project.learningmanagemtsystem.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int courseId;
//    @NotNull(message = "Course Name Should Not Be Empty")
    @Column(name = "course_name", nullable = false)
    private String courseName;
    @Column(name = "image_url",columnDefinition = "TEXT")
    private String imageUrl;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    List<CourseDetails> courseDetails = new ArrayList<>();
    @ManyToMany(mappedBy = "courses")
    @JsonIgnoreProperties("courses")
    List<User> students = new ArrayList<>();
//    Changed Hear To User List<Students> Actual changed to User


    public  Courses(){}

    public Courses(int courseId, String courseName, String imageUrl, List<CourseDetails> courseDetails, List<User> users) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.imageUrl = imageUrl;
        this.courseDetails = courseDetails;
        this.students = users;
    }

//    public List<Students> getStudents() {
//        return students;
//    }
//
//    public void setStudents(List<Students> students) {
//        this.students = students;
//    }


    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<CourseDetails> getCourseDetails() {
        return courseDetails;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCourseDetails(List<CourseDetails> courseDetails) {
        this.courseDetails = courseDetails;
    }
}
