package com.ijse.aad_75.Service;

import com.ijse.aad_75.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    void saveStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentDetails(long studentId);
}
