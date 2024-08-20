package com.project.learningmanagemtsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "course_details")
public class CourseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int courserDetailsId;
    @NotNull(message = "Content title Should Not Be Empty")
    @Column(name = "content_title", nullable = false, columnDefinition = "TEXT")
    private String content;
    @NotNull(message = "Description Should Not Be Null")
    @Column(columnDefinition = "TEXT", name = "description", nullable = false)
    private String description;
    @NotNull(message = "CourseId Should Not Be Empty")
    @Column(name = "course_id", nullable = false)
    private int courseId;

    public CourseDetails(){}

    public CourseDetails(int courserDetailsId, String content, String description, int courseId) {
        this.courserDetailsId = courserDetailsId;
        this.content = content;
        this.description = description;
        this.courseId = courseId;
    }

    public int getCourserDetailsId() {
        return courserDetailsId;
    }

    public @NotNull(message = "Description Should Not Be Null") String getDescription() {
        return description;
    }

    public void setDescription(@NotNull(message = "Description Should Not Be Null") String description) {
        this.description = description;
    }

    public void setCourserDetailsId(int courserDetailsId) {
        this.courserDetailsId = courserDetailsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
