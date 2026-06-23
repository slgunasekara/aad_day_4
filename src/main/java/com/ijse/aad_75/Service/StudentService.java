package com.ijse.aad_75.Service;

import com.ijse.aad_75.dto.StudentDTO;
import com.ijse.aad_75.dto.response.GetStudentDTO;

import java.util.List;

public interface StudentService {

    void saveStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudents();

    GetStudentDTO getStudentDetails(long studentId);
}
