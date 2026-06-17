package com.ijse.aad_75.Repository;


import com.ijse.aad_75.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
