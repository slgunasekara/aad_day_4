package com.ijse.aad_75.Service.impl;

import com.ijse.aad_75.Repository.CustomerRepository;
import com.ijse.aad_75.Repository.OrderRepository;
import com.ijse.aad_75.Service.OrderService;
import com.ijse.aad_75.dto.OrderDTO;
import com.ijse.aad_75.dto.response.GetOrderDTO;
import com.ijse.aad_75.entity.Customer;
import com.ijse.aad_75.entity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

//    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
//        this.orderRepository = orderRepository;
//        this.customerRepository = customerRepository;
//    }

    @Override
    public void saveOrder(OrderDTO orderDTO) {
        log.info("Execute method saveOrder()");
        try{

            Order order = new Order();
            order.setOrderDate(orderDTO.getOrderDate());
            order.setTotal(orderDTO.getTotal());
            //////////////////
        //how to get customer id
            //get Customer Repo , so create customer repo eka dependancie injection ekak widihata inject karaganna
            //Front end eken customer id eka ewanawa, eke uniq record eka ganne findById() eken
            //optional data type eka thamai findById() eke data type eka ,
            // mokada id ekak thiyennath puluwan nathi wennath puluwan

            Optional<Customer> optionalCustomer = customerRepository.findById(orderDTO.getCustomerId());
            if(optionalCustomer.isEmpty())
                throw new RuntimeException("Sorry, related customer is not found.");

            //ehema id ekak innawanam customer ta denna
            Customer customer = optionalCustomer.get();

            //order ekata customer wa daanawa
            order.setCustomer(customer);

            //////////////////

            orderRepository.save(order);

        }catch (Exception e){
            log.error("Error in method saveOrder : "+e.getMessage());
            throw e;
        }
    }

    @Override
    public List<OrderDTO> filterOrders(LocalDateTime orderDate) {
        log.info("Execute method filterOrders()");
        try{

            List<OrderDTO> responseList = new ArrayList<>();

            List<Order> orders = orderRepository.filterOrders(orderDate);

            for(Order order : orders){
                OrderDTO dto = new OrderDTO(
                        order.getOrderId(),
                        order.getTotal(),
                        order.getOrderDate());

                responseList.add(dto);
            }

            return responseList;

        }catch (Exception e){
            log.error("Error in method filterOrders : "+e.getMessage());
            throw e;
        }
    }

    @Override
    public GetOrderDTO getOrderDetails(long orderId) {
        //DTO eka OrderDTO nemei ganne GetOrderDTO mokada apata Customer Details wenama ganna awashya wena nisa
        // customize DTO ekak select karanna wenawa - GetOrderDTO
        log.info("Execute method getOrderDetails()");
        try{

            Optional<Order> optionalOrder = orderRepository.findById(orderId);
            if(optionalOrder.isEmpty())
                throw new RuntimeException("Sorry, related order is not found.");

            Order order = optionalOrder.get();
            
            GetOrderDTO responseDTO = new GetOrderDTO();
            responseDTO.setOrderId(order.getOrderId());
            responseDTO.setOrderDate(order.getOrderDate());
            responseDTO.setTotal(order.getTotal());

            //findById kiyala gaththa Order Object eke Save wela thiyena customerwa aragannawa
            Customer customer = order.getCustomer();

            //response ekee e customerta adaala details send karanna
            responseDTO.setCustomerId(customer.getCustomerId());
            responseDTO.setCustomerName(customer.getCustomerName());

            return responseDTO;
        }catch (Exception e){
            log.error("Error in method getOrderDetails : "+e.getMessage());
            throw e;
        }
    }
}
