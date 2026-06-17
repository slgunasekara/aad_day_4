package com.ijse.aad_75.Controller;

import com.ijse.aad_75.Service.OrderService;
import com.ijse.aad_75.constant.CommonResponse;
import com.ijse.aad_75.dto.OrderDTO;
import com.ijse.aad_75.dto.response.GetOrderDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.ijse.aad_75.constant.RespondMessage.SUCCESS;
import static com.ijse.aad_75.constant.ResponseCode.OPERATION_SUCCESS;

@RestController
@RequestMapping(value = "v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveOrder(@RequestBody OrderDTO orderDTO){
        orderService.saveOrder(orderDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterOrders(
            @RequestParam(value = "orderDate",required = false)LocalDateTime orderDate
            ){
        List<OrderDTO> orderDTOS = orderService.filterOrders(orderDate);
        return new CommonResponse(OPERATION_SUCCESS,orderDTOS,SUCCESS);
    }

    @GetMapping(value = "/{orderId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getOrderDetails(@PathVariable long orderId){
        GetOrderDTO orderDetails = orderService.getOrderDetails(orderId);
        return new CommonResponse(OPERATION_SUCCESS,orderDetails,SUCCESS);
    }
}
