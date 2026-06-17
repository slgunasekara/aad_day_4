package com.ijse.aad_75.Service.impl;

import com.ijse.aad_75.Repository.SchoolRepository;
import com.ijse.aad_75.Service.SchoolService;
import com.ijse.aad_75.dto.SchoolDTO;
import com.ijse.aad_75.entity.School;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

//    public SchoolServiceImpl(SchoolRepository schoolRepository) {
//        this.schoolRepository = schoolRepository;
//    }

    @Override
    public void saveSchool(SchoolDTO schoolDTO) {

        log.info("Execute method saveSchool() dto{}",schoolDTO);

        try {
            if(schoolDTO.getSchoolName()==null)
                throw new RuntimeException("School Name is missing");
            if(schoolDTO.getSchoolLocation()==null)
                throw new RuntimeException("School Location is missing");

            School school = new School();
            school.setSchoolName(schoolDTO.getSchoolName());
            school.setSchoolLocation(schoolDTO.getSchoolLocation());

            schoolRepository.save(school);
            log.info("School saved successfully");

        } catch (Exception e) {
            log.error("Error in saveSchool() : {}", e.getMessage());
            throw e;
        }


    }

    @Override
    public List<SchoolDTO> getAllSchools() {
        log.info("Execute method getAllSchools()");
        try {
            List<SchoolDTO> responseList = new ArrayList<>();
            List<School> schoolList = schoolRepository.findAll();

            for (School school : schoolList) {
                SchoolDTO schoolDTO = new SchoolDTO();
                schoolDTO.setSchoolId(school.getSchoolId());
                schoolDTO.setSchoolName(school.getSchoolName());
                schoolDTO.setSchoolLocation(school.getSchoolLocation());
                responseList.add(schoolDTO);
            }
            return responseList;

        } catch (Exception e) {
            log.error("Error in getAllSchools() :{} ",e.getMessage());
            throw e;
        }
    }
}
