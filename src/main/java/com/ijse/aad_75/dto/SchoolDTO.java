package com.ijse.aad_75.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDTO {
    private long schoolId;
    private String schoolName;
    private String schoolLocation;
}
