//package com.project.learningmanagemtsystem.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "students")
//public class Students {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int studentId;
//    @NotNull(message = "UserName Should Not Be Empty")
//    @Column(name = "student_name", nullable = false)
//    private String studentName;
//    @NotNull(message = "Phone Number Should Not Be Empty")
//    @Column(name = "phone_number", nullable = false, unique = true)
//    private String phoneNumber;
//    @NotNull(message = "Email Should Not Be Empty")
//    @Column(name = "email", nullable = false, unique = true)
//    private String email;
//    @NotNull(message = "Password Should Not Be Empty")
//    @Column(name = "password", nullable = false)
//    private String password;
//    @Column(name = "role", nullable = false)
//    private String role = "student";
//    @Column(name = "image_url", nullable = true,columnDefinition = "TEXT")
//    private String imageUrl;
//    @Column(name = "course_taken")
//    private String courseTaken;
//    @Column(name = "completed")
//    private int completed = 0;
//    @Column(name = "total_content")
//    private int totalContent = 0;
//    @OneToMany
//    @JoinColumn(name = "student_id")
//    List<StudentCompleted> studentsCompleted = new ArrayList<>();
//
//
//    @ManyToMany
//    @JoinTable(name = "student_courses", joinColumns = @JoinColumn(name = "studentid"), inverseJoinColumns = @JoinColumn(name = "courseid"))
//    @JsonIgnoreProperties("students")
//    List<Courses> courses = new ArrayList<>();
//
//    public Students(){}
//
//
//
//    public Students(String studentName, int studentId, String phoneNumber, String email, String password, String role, String imageUrl, String courseTaken, int completed, int totalContent, List<StudentCompleted> studentsCompleted, List<Courses> courses) {
//        this.studentName = studentName;
//        this.studentId = studentId;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.password = password;
//        this.role = role;
//        this.imageUrl = imageUrl;
//        this.courseTaken = courseTaken;
//        this.completed = completed;
//        this.totalContent = totalContent;
//        this.studentsCompleted = studentsCompleted;
//        this.courses = courses;
//    }
//
//    public List<StudentCompleted> getStudentsCompleted() {
//        return studentsCompleted;
//    }
//
//    public void setStudentsCompleted(List<StudentCompleted> studentsCompleted) {
//        this.studentsCompleted = studentsCompleted;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public List<Courses> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(List<Courses> courses) {
//        this.courses = courses;
//    }
//
//    public int getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(int studentId) {
//        this.studentId = studentId;
//    }
//
//    public @NotNull(message = "UserName Should Not Be Empty") String getStudentName() {
//        return studentName;
//    }
//
//    public void setStudentName(@NotNull(message = "UserName Should Not Be Empty") String studentName) {
//        this.studentName = studentName;
//    }
//
//    public @NotNull(message = "Phone Number Should Not Be Empty") String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(@NotNull(message = "Phone Number Should Not Be Empty") String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public @NotNull(message = "Email Should Not Be Empty") String getEmail() {
//        return email;
//    }
//
//    public void setEmail(@NotNull(message = "Email Should Not Be Empty") String email) {
//        this.email = email;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    public String getCourseTaken() {
//        return courseTaken;
//    }
//
//    public void setCourseTaken(String courseTaken) {
//        this.courseTaken = courseTaken;
//    }
//
//    public int getCompleted() {
//        return completed;
//    }
//
//    public void setCompleted(int completed) {
//        this.completed = completed;
//    }
//
//    public int getTotalContent() {
//        return totalContent;
//    }
//
//    public void setTotalContent(int totalContent) {
//        this.totalContent = totalContent;
//    }
//   }
