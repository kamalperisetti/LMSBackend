//package com.project.learningmanagemtsystem.service;
//
//import com.project.learningmanagemtsystem.model.Courses;
//import com.project.learningmanagemtsystem.model.StudentCompleted;
//import com.project.learningmanagemtsystem.model.Students;
//import com.project.learningmanagemtsystem.repository.CoursesJpaRepository;
//import com.project.learningmanagemtsystem.repository.StudentRepository;
//import com.project.learningmanagemtsystem.repository.StudentsJpaRepository;
//import com.project.learningmanagemtsystem.validations.ValidationImplementaion;
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.ConstraintViolationException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class StudentJpaService implements StudentRepository {
//    @Autowired
//    private StudentsJpaRepository studentsJpaRepository;
//    @Autowired
//    private CoursesJpaRepository coursesJpaRepository;
//
//    @Override
//    public List<Students> getAllStudents() {
//        List<Students> students = studentsJpaRepository.findAll();
//        return students;
//    }
//
//    @Override
//    public Students addStudent(Students students) {
//        try{
//            if(ValidationImplementaion.mobileNumberValidation(students.getPhoneNumber())){
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Provide a Valid Phone Number");
//            }
//            if(ValidationImplementaion.emailValidation(students.getEmail())){
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Provide a Valid Email ID");
//            }
//            List<Integer> courseIds = new ArrayList<>();
//            for(Courses course : students.getCourses()){
//                courseIds.add(course.getCourseId());
//            }
//            List<Courses> courses = coursesJpaRepository.findAllById(courseIds);
//            students.setCourses(courses);
//            studentsJpaRepository.save(students);
//            return students;
//        }catch (ConstraintViolationException e){
//            Set<ConstraintViolation<?>> violations =  e.getConstraintViolations();
//            String errorMessage = violations.stream()
//                    .map(ConstraintViolation::getMessage)
//                    .collect(Collectors.joining(", "));
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
//        }catch (DataIntegrityViolationException e){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicated data found. Check for any duplication in the data and phone number , email should be unique.");
//        }catch (ResponseStatusException e){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getReason());
//        } catch (Exception e){
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong.");
//        }
//
//    }
//
//    @Override
//    public Students getStudentById(int studentId) {
//        try{
//            Students student = studentsJpaRepository.findById(studentId).get();
//            return student;
//        }catch (Exception e){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found With This ID");
//        }
//    }
//
//    @Override
//    public Students updateStudent(int studentId, Students students) {
//        try{
//            Students exisitingStudent = studentsJpaRepository.findById(studentId).get();
//            if(students.getStudentName() != null){
//                exisitingStudent.setStudentName(students.getStudentName());
//            }
//            if(students.getPhoneNumber()!= null){
//                if(ValidationImplementaion.mobileNumberValidation(students.getPhoneNumber())){
//                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Provide a Valid Phone Number");
//                }
//                exisitingStudent.setPhoneNumber(students.getPhoneNumber());
//            }
//            if(students.getEmail() != null){
//                if(ValidationImplementaion.emailValidation(students.getEmail())){
//                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Provide a Valid Email ID");
//                }
//                exisitingStudent.setEmail(students.getEmail());
//            }
//            if(students.getImageUrl() != null){
//                exisitingStudent.setImageUrl((students.getImageUrl()));
//            }
//            if(students.getCourses() != null){
//                List<Integer> courseIds = new ArrayList<>();
//                for(Courses course : students.getCourses()){
//                    courseIds.add(course.getCourseId());
//                }
//                List<Courses> courses = coursesJpaRepository.findAllById(courseIds);
//                exisitingStudent.setCourses(courses);
//            }
////            if(students.getStudentsCompleted() != null){
////               List<StudentCompleted> studentCompleted = students.getStudentsCompleted();
////               for(StudentCompleted studentCompleted1 : studentCompleted){
////                   int studentIDD = studentCompleted1.getStudentId();
////
////               }
////            }
//            if(students.getCourseTaken() != null){
//                exisitingStudent.setCourseTaken(students.getCourseTaken());
//            }
//            if(students.getCompleted() > 0){
//                exisitingStudent.setCompleted(students.getCompleted());
//            }
//            if(students.getTotalContent() > 0){
//                exisitingStudent.setTotalContent(students.getTotalContent());
//            }
//            studentsJpaRepository.save(exisitingStudent);
//            return exisitingStudent;
//        }catch (Exception e){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found With This ID");
//        }
//    }
//
//    @Override
//    public void deleteStudent(int studentId) {
//        Optional<Students> student = studentsJpaRepository.findById(studentId);
//        if(student.isPresent()){
//            studentsJpaRepository.deleteById(studentId);
//            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Student Deleted Successfully");
//        }else{
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found With This ID");
//        }
//    }
//
//    @Override
//    public Students findByPhoneNumber(Students students) {
//            Students student = studentsJpaRepository.findByPhoneNumber(students.getPhoneNumber());
//            if(student == null){
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found With This Number");
//            } else if (!student.getPassword().equals(students.getPassword())) {
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Password doesn't match please Provide valid password");
//            } else{
//               return student;
//            }
//    }
//}
