package com.ijse.aad_75.Service;

import com.ijse.aad_75.dto.DepartmentDTO;
import com.ijse.aad_75.dto.request.UpdateDepartmentDTO;

import java.util.List;

public interface DepartmentService {
    public void saveDepartment(DepartmentDTO departmentDTO);

    public List<DepartmentDTO> getAllDepartment();

    public DepartmentDTO getDepartmentDetails(long departmentId);

    //front end eken ena data tika thamai methanata watenne
    void updateDepartment(DepartmentDTO departmentDTO);

    void updateLocation(UpdateDepartmentDTO updateDepartmentDTO);


    List<DepartmentDTO> filterDepartment(String departmentName, String departmentLocation);



//    List<DepartmentDTO> filterDepartmentLocation(String departmentLocation);
}
