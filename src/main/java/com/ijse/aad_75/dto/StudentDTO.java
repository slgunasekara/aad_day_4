package com.ijse.aad_75.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private long studentId;
    private String studentFirstName;
    private String studentLastName;
    private String contact;

    private long schoolId;

    public StudentDTO(long studentId, String studentFirstName, String studentLastName, String contact) {
        this.studentId = studentId;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.contact = contact;
    }

}
