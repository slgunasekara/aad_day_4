package com.ijse.aad_75.Controller;

import com.ijse.aad_75.Service.SchoolService;
import com.ijse.aad_75.constant.CommonResponse;
import com.ijse.aad_75.dto.SchoolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ijse.aad_75.constant.RespondMessage.SUCCESS;
import static com.ijse.aad_75.constant.ResponseCode.OPERATION_SUCCESS;

@RestController
@RequestMapping(value = "v1/schools")

public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveSchool(@RequestBody SchoolDTO schoolDTO){
        schoolService.saveSchool(schoolDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllSchools(){
        List<SchoolDTO> allSchools = schoolService.getAllSchools();
        return new CommonResponse(OPERATION_SUCCESS,allSchools,SUCCESS);
    }
 }
