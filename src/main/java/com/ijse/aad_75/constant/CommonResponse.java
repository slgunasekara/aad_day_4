package com.ijse.aad_75.constant;

import com.ijse.aad_75.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CommonResponse {

    private  int status;
    //1 - data not success
    //0 - data success
    private Object body;
    //response data eka thiyaagena inne meya
    private String message;
    //data ekak save karaama save kiyala notify karanne meyaa

    //awashya welaawata awashya eka ganna customize constructors daanna ona
    public CommonResponse(int operationSuccess, String success) {
        this.status = operationSuccess;
        this.message = success;
    }
}
