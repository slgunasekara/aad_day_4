package com.ijse.aad_75.Service.impl;

import com.ijse.aad_75.Repository.SchoolRepository;
import com.ijse.aad_75.Repository.StudentRepository;
import com.ijse.aad_75.Service.StudentService;
import com.ijse.aad_75.dto.StudentDTO;
import com.ijse.aad_75.entity.School;
import com.ijse.aad_75.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;

//    public StudentServiceImpl(StudentRepository studentRepository, SchoolRepository schoolRepository) {
//        this.studentRepository = studentRepository;
//        this.schoolRepository = schoolRepository;
//    }

    @Override
    public void saveStudent(StudentDTO studentDTO) {
        log.info("Execute method saveStudent() dto{}",studentDTO);
        try{

            if(studentDTO.getStudentFirstName()==null)
                throw new RuntimeException("Student First name is missing");

            Student student = new Student();
            student.setStudentFirstName(studentDTO.getStudentFirstName());
            student.setStudentLastName(studentDTO.getStudentLastName());
            student.setContact(studentDTO.getContact());

            Optional<School> optionalSchool = schoolRepository.findById(studentDTO.getSchoolId());
            if(optionalSchool.isEmpty())
                throw new RuntimeException("Sorry, related school is not found.");

            School school = optionalSchool.get();

            student.setSchool(school);
            studentRepository.save(student);

        }catch (Exception e){
            log.error("Error in method saveStudent : "+e.getMessage());
            throw e;
        }
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        log.info("Execute method getAllStudents()");
        try{

            List<StudentDTO> responseList=new ArrayList<>();

            List<Student> studentList = studentRepository.findAll();

            for(Student student : studentList){

                StudentDTO dto = new StudentDTO(student.getStudentId(),
                        student.getStudentFirstName(),student.getStudentLastName(),
                        student.getContact());

                responseList.add(dto);

            }

            return responseList;

        }catch (Exception e){
            log.error("Error in method getAllStudents : "+e.getMessage());
            throw e;
        }
    }

    @Override
    public StudentDTO getStudentDetails(long studentId) {
        log.info("Execute method getStudentDetails() studentId:{}",studentId);
        try{
            Optional<Student> optionalStudent = studentRepository.findById(studentId);
            if(optionalStudent.isEmpty())
                throw new RuntimeException("Sorry, related student is not found.");

            Student student = optionalStudent.get();
//            StudentDTO studentDTO = new StudentDTO();
//            studentDTO.setStudentId(student.getStudentId());
//            studentDTO.setStudentFirstName(student.getStudentFirstName());
//            studentDTO.setStudentLastName(student.getStudentLastName());
//            studentDTO.setContact(student.getContact());
//            return studentDTO;
            return new StudentDTO(student.getStudentId(),
                    student.getStudentFirstName(),student.getStudentLastName(),
                    student.getContact());


        } catch (Exception e){
            log.error("Error in method getStudentDetails : "+e.getMessage());
            throw e;
        }
    }


}
