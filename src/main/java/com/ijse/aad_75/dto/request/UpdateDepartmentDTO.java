package com.ijse.aad_75.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDepartmentDTO {
    private long  departmentId;
    private String departmentLocation;
}