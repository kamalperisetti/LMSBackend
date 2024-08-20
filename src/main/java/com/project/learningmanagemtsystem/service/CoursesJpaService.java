package com.project.learningmanagemtsystem.service;

import com.project.learningmanagemtsystem.model.CourseDetails;
import com.project.learningmanagemtsystem.model.Courses;
import com.project.learningmanagemtsystem.repository.CourseDetailsJpaRepository;
import com.project.learningmanagemtsystem.repository.CoursesJpaRepository;
import com.project.learningmanagemtsystem.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoursesJpaService implements CoursesRepository {
    @Autowired
    private CoursesJpaRepository coursesJpaRepository;

    @Autowired
    private CourseDetailsJpaRepository courseDetailsJpaRepository;
    @Override
    public List<Courses> getAllCourses() {
        List<Courses> courses =  coursesJpaRepository.findAll();
        return courses;
    }

    @Override
    public Courses addCourse(Courses courses) {
        Optional<Courses> course = coursesJpaRepository.findByCourseName(courses.getCourseName());
        if(course.isPresent()){
//            System.out.println(courses.getImageUrl());
//            System.out.println(course.get().getCourseId() + "MY OWN");
//
//            updateCourse(course.get().getCourseId(), courses);
            return course.get();
        }else{
            List<CourseDetails> courseDetails = courses.getCourseDetails();
            ArrayList<Integer> courseDetailsIds = new ArrayList<>();
            for(CourseDetails singelCourseDetailes : courseDetails){
                courseDetailsIds.add(singelCourseDetailes.getCourserDetailsId());
            }
            courses.setCourseDetails(courseDetailsJpaRepository.findAllById(courseDetailsIds));
            coursesJpaRepository.save(courses);
            return courses;
        }
//        List<CourseDetails> courseDetails = courses.getCourseDetails();
//        ArrayList<Integer> courseDetailsIds = new ArrayList<>();
//        for(CourseDetails singelCourseDetailes : courseDetails){
//            courseDetailsIds.add(singelCourseDetailes.getCourserDetailsId());
//        }
//        courses.setCourseDetails(courseDetailsJpaRepository.findAllById(courseDetailsIds));
//        coursesJpaRepository.save(courses);
//        return courses;
    }

    @Override
    public Courses updateCourse(int courseId, Courses courses) {
        List<CourseDetails> courseDetails = courses.getCourseDetails();
        try{
            Courses existingCourse = coursesJpaRepository.findById(courseId).get();

            if(courses.getCourseName() != null){
                existingCourse.setCourseName(courses.getCourseName());
            }
            if(courses.getImageUrl() != null){
                existingCourse.setImageUrl(courses.getImageUrl());
            }
            if(courses.getCourseDetails() != null){
                ArrayList<Integer> courseDetailsIds = new ArrayList<>();

                for(CourseDetails singelCourseDetailes : courseDetails){
                    courseDetailsIds.add(singelCourseDetailes.getCourserDetailsId());
                }
                existingCourse.setCourseDetails(courseDetailsJpaRepository.findAllById(courseDetailsIds));
            }
            coursesJpaRepository.save(existingCourse);
            return existingCourse;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Not Found With This ID, HERE");
        }

    }

    @Override
    public Courses getCourseById(int courseId) {
        try{
           Courses course =  coursesJpaRepository.findById(courseId).get();
           return  course;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Not Found With This ID");
        }
    }

    @Override
    public void deleteCourseById(int courseId) {
//        first delete the relation with the details table when course is deleting corresponding course details are not deleting

        Optional<Courses> course = coursesJpaRepository.findById(courseId);
        if(course.isPresent()){
            coursesJpaRepository.deleteById(courseId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course with this courseId not found");
        }
    }

    @Override
    public void deleteCourseByName(Courses courses) {
        Optional<Courses> course = coursesJpaRepository.findByCourseName(courses.getCourseName());
        if(course.isPresent()){
            int courseId = course.get().getCourseId();
            List<CourseDetails> courseDetails = courseDetailsJpaRepository.findByCourseId(courseId);
            List<Integer> courseDetailsIds = new ArrayList<>();
            for(CourseDetails courseDetails1 : courseDetails){
                courseDetailsIds.add(courseDetails1.getCourserDetailsId());
            }
            courseDetailsJpaRepository.deleteAllById(courseDetailsIds);
            coursesJpaRepository.deleteById(course.get().getCourseId());
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Deleted Successfully");
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Such Course Found With This Name");
        }
    }
}
