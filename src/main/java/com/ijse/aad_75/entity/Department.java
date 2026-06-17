package com.ijse.aad_75.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data//gives setters() getters() toString() equals()
@NoArgsConstructor//default contructors
@AllArgsConstructor//all arguments constructors
@Entity//defines this class as a entity / table
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto increment
    private long  departmentId;
    private String departmentName;
    private String departmentLocation;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<EmployeeDepartment> employeeDepartments;
}
