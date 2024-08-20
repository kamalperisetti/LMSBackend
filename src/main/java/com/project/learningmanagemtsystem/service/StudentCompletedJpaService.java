package com.project.learningmanagemtsystem.service;

import com.project.learningmanagemtsystem.model.StudentCompleted;
import com.project.learningmanagemtsystem.repository.StudentCompletedJpaRepository;
import com.project.learningmanagemtsystem.repository.StudentCompletedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StudentCompletedJpaService implements StudentCompletedRepository {
    @Autowired
    private StudentCompletedJpaRepository studentCompletedJpaRepository;
    @Override
    public List<StudentCompleted> allStudentCompleted() {
        List<StudentCompleted> studentCompleted = studentCompletedJpaRepository.findAll();
        return studentCompleted;
    }

    @Override
    public StudentCompleted addStudentCompleted(StudentCompleted studentCompleted) {
        if(studentCompleted.getStudentId() > 0 ) {
            List<StudentCompleted> studentCompletedList = studentCompletedJpaRepository.findByStudentId(studentCompleted.getStudentId());
//            StudentCompleted studentCompleted2 = studentCompletedJpaRepository.findByCourseId(studentCompleted.getCourseId());
//            System.out.println(studentCompleted1.getStudentId()+ "Inside");
            if(!studentCompletedList.isEmpty()){
                for(StudentCompleted studentCompleted2: studentCompletedList){
                    if(studentCompleted2.getCourseId() == studentCompleted.getCourseId()){
                        updateStudentCompleted(studentCompleted2.getStudentCompletedId(), studentCompleted);
                        System.out.println("InsideThis");
                        return studentCompleted;
                    }
                }
            }
        }
        System.out.println(studentCompleted.getStudentId()+ "OutsideTHis");
        return studentCompletedJpaRepository.save(studentCompleted);

    }


    @Override
    public StudentCompleted updateStudentCompleted(int studentCompletedId, StudentCompleted studentCompleted) {
        try{
            StudentCompleted existingStudentCompleted = studentCompletedJpaRepository.findById(studentCompletedId).get();
            if(studentCompleted.getStudentId() > 0){
                existingStudentCompleted.setStudentId(studentCompleted.getStudentId());
            }if(studentCompleted.getCourseId() > 0){
                existingStudentCompleted.setCourseId(studentCompleted.getCourseId());
            }
            if(studentCompleted.getTotalContent() > 0){
                existingStudentCompleted.setTotalContent(studentCompleted.getTotalContent());
            }
            if(studentCompleted.getCompleted() >0){
                existingStudentCompleted.setCompleted(studentCompleted.getCompleted());
            }
            studentCompletedJpaRepository.save(existingStudentCompleted);
            return existingStudentCompleted;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Complete Table Not Found");
        }
    }

    @Override
    public void deleteStudentCompleted(int studentCompletedId) {

            Optional<StudentCompleted> studentCompleted = studentCompletedJpaRepository.findById(studentCompletedId);
            if(studentCompleted.isPresent()){
                studentCompletedJpaRepository.deleteById(studentCompletedId);
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Deleted Su");
            }else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Completed Not Found");
            }

    }

    @Override
    public String updateStudentCompletedByCourseId(int courseId, StudentCompleted studentCompleted) {
        try{
            List<StudentCompleted> studentCompleted1 = studentCompletedJpaRepository.findByCourseId(courseId);
//            StudentCompleted updatedOne = updateStudentCompleted(studentCompleted1.getStudentCompletedId(), studentCompleted);
            for(StudentCompleted studentCompleted2 : studentCompleted1){
                if(studentCompleted2.getStudentId() == studentCompleted.getStudentId()){
                    if(studentCompleted2.getCompleted() + studentCompleted.getCompleted() < studentCompleted2.getTotalContent()){
                        studentCompleted2.setCompleted(studentCompleted2.getCompleted() + studentCompleted.getCompleted());

                    }else{
                        studentCompleted2.setCompleted(studentCompleted2.getTotalContent());
                    }
//                    StudentCompleted updatedOne = updateStudentCompleted(studentCompleted2.getStudentCompletedId(), studentCompleted);
//                    studentCompletedJpaRepository.save(studentCompleted2);
                    studentCompletedJpaRepository.save(studentCompleted2);
                    return "updatedOne Success";
                }
//            return studentCompleted;
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Not Found With This CourseId");
        }
        return "Smething Something";

    }


}
