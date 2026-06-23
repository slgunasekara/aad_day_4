package com.ijse.aad_75.Service.impl;

import com.ijse.aad_75.Repository.SchoolRepository;
import com.ijse.aad_75.Repository.StudentRepository;
import com.ijse.aad_75.Service.StudentService;
import com.ijse.aad_75.dto.StudentDTO;
import com.ijse.aad_75.dto.response.GetStudentDTO;
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

            //front end eken ewana school id eka optional ekak widihata pass karanawa findByID eken aragen
            Optional<School> optionalSchool = schoolRepository.findById(studentDTO.getSchoolId());

            if(optionalSchool.isEmpty())
                throw new RuntimeException("Sorry, related school is not found.");

            //optional school eka aragannawa
            School school = optionalSchool.get();

            //e aragaththa optional school eka student ta set karanawa
            student.setSchool(school);
            //setup karapu studentwa save karanawa database ekata
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
    public GetStudentDTO getStudentDetails(long studentId) {
        log.info("Execute method getStudentDetails() studentId:{}",studentId);
        try{
            Optional<Student> optionalStudent = studentRepository.findById(studentId);
            if(optionalStudent.isEmpty())
                throw new RuntimeException("Sorry, related student is not found.");


            Student student = optionalStudent.get();

            GetStudentDTO responseDTO = new GetStudentDTO();
            responseDTO.setStudentId(student.getStudentId());
            responseDTO.setStudentFirstName(student.getStudentFirstName());
            responseDTO.setStudentLastName(student.getStudentLastName());
            responseDTO.setContact(student.getContact());

            School school = student.getSchool();

            responseDTO.setSchoolId(school.getSchoolId());
            responseDTO.setSchoolName(school.getSchoolName());
            return responseDTO;


        } catch (Exception e){
            log.error("Error in method getStudentDetails : "+e.getMessage());
            throw e;
        }
    }


}
