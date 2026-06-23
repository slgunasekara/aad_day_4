package com.ijse.aad_75.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetStudentDTO {
    private long studentId;
    private String studentFirstName;
    private String studentLastName;
    private String contact;

    private long schoolId;
    private String schoolName;
}
