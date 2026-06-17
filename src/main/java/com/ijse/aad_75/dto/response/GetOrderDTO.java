package com.ijse.aad_75.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderDTO {

    private long orderId;
    private double total;
    private LocalDateTime orderDate;

    private long customerId;
    private String customerName;
}
