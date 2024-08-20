package com.project.learningmanagemtsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Integer studentId;
    @NotEmpty(message = "UserName Should Not Be Empty")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Username Should Not Be Empty")
    @Column(name = "username",  unique = true)
    private String username;
    @NotEmpty(message = "Phone Number Should Not Be Empty")
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @NotEmpty(message = "Email Should Not Be Empty")
    @Column(name = "email",  unique = true)
    private String email;

    @NotEmpty(message = "Password Should Not Be Empty")
    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Column(name = "image_url", nullable = true,columnDefinition = "TEXT")
    private String imageUrl;
    @OneToMany
    @JoinColumn(name = "student_id")
    List<StudentCompleted> studentsCompleted = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "student_courses", joinColumns = @JoinColumn(name = "studentid"), inverseJoinColumns = @JoinColumn(name = "courseid"))
    @JsonIgnoreProperties("students")
    List<Courses> courses = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Token> tokens;

    public User(){}


    public User(Integer studentId, String firstName, String lastName, String username, String phoneNumber, String email, String password, Role role, String imageUrl, List<StudentCompleted> studentsCompleted, List<Courses> courses, List<Token> tokens) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
        this.imageUrl = imageUrl;
        this.studentsCompleted = studentsCompleted;
        this.courses = courses;
        this.tokens = tokens;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber( String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public  String getEmail() {
        return email;
    }

    public void setEmail( String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<StudentCompleted> getStudentsCompleted() {
        return studentsCompleted;
    }

    public void setStudentsCompleted(List<StudentCompleted> studentsCompleted) {
        this.studentsCompleted = studentsCompleted;
    }
}
