package com.ijse.aad_75.Repository;

import com.ijse.aad_75.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

//    @Query(value = "select d from Department d where d.departmentName like %?1%", nativeQuery = true)
//    List<Department> filterDepartments(String departmentName);

//    @Query(value = "select d from Department d where d.departmentLocation like %?1%", nativeQuery = true)
//    List<Department> filterDepartmentLocation(String departmentLocation);

    @Query(value = "SELECT * FROM department " +
            "WHERE (?1 IS NULL OR departmentName LIKE %?1%) AND " +
            "(?2 IS NULL OR departmentLocation LIKE %?2%)",nativeQuery = true)
    List<Department> filterDepartments(String departmentName, String departmentLocation);


}
