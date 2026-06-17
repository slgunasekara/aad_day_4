package com.ijse.aad_75.Service.impl;

import com.ijse.aad_75.Repository.CustomerRepository;
import com.ijse.aad_75.Service.CustomerService;
import com.ijse.aad_75.dto.CustomerDTO;
import com.ijse.aad_75.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

//    public CustomerServiceImpl(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        log.info("Execute method saveCustomer()");

        try{

            Customer customer = new Customer();
            customer.setCustomerName(customerDTO.getCustomerName());
            customer.setContact(customerDTO.getContact());

            customerRepository.save(customer);

        }catch (Exception e){
            log.error("Error in saveCustomer : "+e.getMessage());
            throw e;
        }
    }

    @Override
    public List<CustomerDTO> filterCustomers(String customerName) {
        log.info("Execute method filterCustomers()");

        try{

            List<CustomerDTO> responseList = new ArrayList<>();

            List<Customer> customerList = customerRepository.filterCustomers(customerName);

            for(Customer customer : customerList){

                CustomerDTO dto = new CustomerDTO(customer.getCustomerId(),
                        customer.getCustomerName(),customer.getContact());

                responseList.add(dto);
            }

            return responseList;

        }catch (Exception e){
            log.error("Error in filterCustomers : "+e.getMessage());
            throw e;
        }
    }

    @Override
    public CustomerDTO getCustomerDetails(long customerId) {
        log.info("Execute method getCustomerDetails()");
        try{

            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            if(optionalCustomer.isEmpty())
                throw new RuntimeException(("Sorry, related customer is not found"));

            Customer customer = optionalCustomer.get();

            return new CustomerDTO(customer.getCustomerId(),
                    customer.getCustomerName(),customer.getContact());

        }catch (Exception e){
            log.error("Error in getCustomerDetails : "+e.getMessage());
            throw e;
        }
    }
}
