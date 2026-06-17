package com.ijse.aad_75.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private long orderId;
    private double total;
    private LocalDateTime orderDate;

    private long customerId;

    public OrderDTO(long orderId, double total, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.total = total;
        this.orderDate = orderDate;
    }
}
