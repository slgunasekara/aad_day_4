package com.ijse.aad_75.Controller;

import com.ijse.aad_75.Service.CustomerService;
import com.ijse.aad_75.constant.CommonResponse;
import com.ijse.aad_75.dto.CustomerDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ijse.aad_75.constant.RespondMessage.SUCCESS;
import static com.ijse.aad_75.constant.ResponseCode.OPERATION_SUCCESS;

@RestController
@RequestMapping(value = "v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.saveCustomer(customerDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterCustomers(
            @RequestParam(value = "customerName",required = false) String customerName
    ){
        List<CustomerDTO> customerDTOS = customerService.filterCustomers(customerName);
        return new CommonResponse(OPERATION_SUCCESS,customerDTOS,SUCCESS);
    }

    @GetMapping(value = "/{customerId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getCustomerDetails(@PathVariable long customerId){
        CustomerDTO customerDetails = customerService.getCustomerDetails(customerId);
        return new CommonResponse(OPERATION_SUCCESS,customerDetails,SUCCESS);
    }
}
