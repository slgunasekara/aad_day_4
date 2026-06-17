package com.ijse.aad_75.Service;

import com.ijse.aad_75.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    void saveCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> filterCustomers(String customerName);

    CustomerDTO getCustomerDetails(long customerId);
}
