package com.ijse.aad_75.Service;

import com.ijse.aad_75.dto.EmployeeDTO;
import com.ijse.aad_75.dto.request.UpdateEmployeeDTO;

import java.util.List;

public interface EmployeeService {

    public void saveEmployee(EmployeeDTO employeeDTO);

    public List<EmployeeDTO> getAllEmployee();

    EmployeeDTO getEmployeeDetails(long employeeId);
    //frint end eken ewanne primary key ekak eka nisa eya ewanne long ekak nisa long daanawa

    void updateEmployee(EmployeeDTO employeeDTO) ;

    void updateAddress(UpdateEmployeeDTO updateEmployeeDTO);

    void changeEmployeeStatus(long employeeId);
}
