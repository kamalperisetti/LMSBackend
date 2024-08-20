package com.project.learningmanagemtsystem.service;

import com.project.learningmanagemtsystem.model.CourseDetails;
import com.project.learningmanagemtsystem.repository.CourseDetailsJpaRepository;
import com.project.learningmanagemtsystem.repository.CourseDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CourseDetailsJpaService implements CourseDetailsRepository {
    @Autowired
    private CourseDetailsJpaRepository courseDetailsJpaRepository;
    @Override
    public List<CourseDetails> getAllCourseDetails() {
        List<CourseDetails> courseDetails = courseDetailsJpaRepository.findAll();
        return  courseDetails;
    }

    @Override
    public CourseDetails getCourseDetailsById(int courserDetailsId) {
        try{
            CourseDetails courseDetails = courseDetailsJpaRepository.findById(courserDetailsId).get();
            return  courseDetails;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CourseDetails Not Found With This ID");
        }
    }

    @Override
    public CourseDetails updateCourseDetails(int courseDetailsId, CourseDetails courseDetails) {
        try {
            CourseDetails existingCourseDetails = courseDetailsJpaRepository.findById(courseDetailsId).get();
            if(courseDetails.getContent() != null){
                existingCourseDetails.setContent(courseDetails.getContent());
            }
            if(courseDetails.getDescription() != null){
                existingCourseDetails.setDescription(courseDetails.getDescription());
            }
            if(courseDetails.getCourseId() != 0){
                existingCourseDetails.setCourseId(courseDetails.getCourseId());
            }
            courseDetailsJpaRepository.save(existingCourseDetails);
            return existingCourseDetails;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CourseDetails Not Found With This Id");
        }
    }

    @Override
    public CourseDetails addCourseDetails(CourseDetails courseDetails) {
        return  courseDetailsJpaRepository.save(courseDetails);
    }

    @Override
    public void deleteCourseDetailsById(int courserDetailsId) {
        Optional<CourseDetails> courseDetails = courseDetailsJpaRepository.findById(courserDetailsId);
        if(courseDetails.isPresent()){
            courseDetailsJpaRepository.deleteById(courserDetailsId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CourseDetails Not Found With This ID");
        }
    }

    @Override
    public List<CourseDetails> findCourseByCourseId(int courseId) {
        List<CourseDetails> courseDetails = courseDetailsJpaRepository.findByCourseId(courseId);
        return  courseDetails;
    }
}
