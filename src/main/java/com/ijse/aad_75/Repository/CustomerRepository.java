package com.ijse.aad_75.Repository;

import com.ijse.aad_75.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query(value = "SELECT * FROM customer " +
            "WHERE (?1 IS NULL OR customer_name LIKE %?1%)",nativeQuery = true)
    List<Customer> filterCustomers(String customerName);
}
