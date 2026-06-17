package com.ijse.aad_75.Service;

import com.ijse.aad_75.dto.OrderDTO;
import com.ijse.aad_75.dto.response.GetOrderDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    void saveOrder(OrderDTO orderDTO);

    List<OrderDTO> filterOrders(LocalDateTime orderDate);

    GetOrderDTO getOrderDetails(long orderId);
}
