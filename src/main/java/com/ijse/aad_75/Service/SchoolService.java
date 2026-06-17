package com.ijse.aad_75.Service;

import com.ijse.aad_75.dto.SchoolDTO;

import java.util.List;

public interface SchoolService {

    void saveSchool(SchoolDTO schoolDTO);

    List<SchoolDTO> getAllSchools();
}
