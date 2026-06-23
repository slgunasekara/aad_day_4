package com.ijse.aad_75.Controller;

import com.ijse.aad_75.Service.StudentService;
import com.ijse.aad_75.constant.CommonResponse;
import com.ijse.aad_75.dto.StudentDTO;
import com.ijse.aad_75.dto.response.GetStudentDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.ijse.aad_75.constant.RespondMessage.SUCCESS;
import static com.ijse.aad_75.constant.ResponseCode.OPERATION_SUCCESS;

@RestController
@RequestMapping(value = "v1/students")
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveStudent(@RequestBody StudentDTO studentDTO){
        studentService.saveStudent(studentDTO);
//        return "Student Saved...";
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllStudents(){
//        return studentService.getAllStudents();
        return new CommonResponse(OPERATION_SUCCESS,
                studentService.getAllStudents(),SUCCESS);
    }

    @GetMapping(value = "/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getStudentDetails(@PathVariable long studentId){
        GetStudentDTO studentDetails = studentService.getStudentDetails(studentId);
        return new CommonResponse(OPERATION_SUCCESS,studentDetails,SUCCESS);
    }
}
