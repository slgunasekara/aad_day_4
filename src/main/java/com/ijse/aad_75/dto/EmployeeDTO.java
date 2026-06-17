package com.ijse.aad_75.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data//gives setters() getters() toString() equals()
@NoArgsConstructor//default contructors
@AllArgsConstructor//all arguments constructors
public class EmployeeDTO {
    private long  employeeId;
    private String firstname;
    private String lastname;
    private String address;

    //date eke type eka LocalDate
    private LocalDateTime joinDate;
}
