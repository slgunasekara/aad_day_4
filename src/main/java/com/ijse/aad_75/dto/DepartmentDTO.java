package com.ijse.aad_75.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//gives setters() getters() toString() equals()
@NoArgsConstructor//default contructors
@AllArgsConstructor//all arguments constructors
public class DepartmentDTO {
    private long  departmentId;
    private String departmentName;
    private String departmentLocation;
}
