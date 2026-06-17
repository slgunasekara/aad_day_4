package com.ijse.aad_75.entity;

import com.ijse.aad_75.enumaration.EmployeeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
//entity class ekak aniwaaren Encapsulated class ekak wiya yuthui


@Data//gives setters() getters() toString() equals()
@NoArgsConstructor//default contructors
@AllArgsConstructor//all arguments constructors
@Entity//defines this class as a entity / table
public class Employee {


    @Id//define this variable as a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto increment
    private long  employeeId;
    private String firstname;
    private String lastname;
    private String address;
    private LocalDateTime joinDate;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<EmployeeDepartment> employeeDepartments;
}
