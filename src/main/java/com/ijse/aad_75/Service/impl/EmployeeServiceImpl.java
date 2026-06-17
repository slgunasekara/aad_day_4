package com.ijse.aad_75.Service.impl;

import com.ijse.aad_75.Repository.EmployeeRepository;
import com.ijse.aad_75.Service.EmployeeService;
import com.ijse.aad_75.dto.EmployeeDTO;
import com.ijse.aad_75.dto.request.UpdateEmployeeDTO;
import com.ijse.aad_75.entity.Employee;
import com.ijse.aad_75.enumaration.EmployeeStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor//Constructor dependancies daapu nisa meka awashya na
//private final monawa hari karoth eya hadaagena denawa apata

public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;

//    //constructore eka harahaa thamai api initialize karanne
//    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }

    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {

        try {
            log.info("Execute method saveEmployee");
            //Entity object create කරනවා
            Employee employee = new Employee();
            employee.setFirstname(employeeDTO.getFirstname());
            employee.setLastname(employeeDTO.getLastname());
            employee.setAddress(employeeDTO.getAddress());
            employee.setJoinDate(LocalDateTime.now());

            //save method eka denne Spoing data base haraha
            employeeRepository.save(employee);
            log.info("Employee saved successfully");
        } catch (Exception e) {
            log.error("Error in saveEmployee", e);
            throw new RuntimeException(e);
        }

    }

    //awashya karanne EmployeeDTO eke data eliyata yawanna
    @Override
    public List<EmployeeDTO> getAllEmployee() {

        try {
            log.info("Execute method getAllEmployee");

            //List ekak haduwa employee DTO daanna array list apply karala (mallak hadaagaththa)
            List<EmployeeDTO> reponseList = new ArrayList<>();
            //sorting machanism ekak dunnoth employeeslawa sort karala dei
            List<Employee> employeeList = employeeRepository.findAll();
            //finalAll inbluild funtion eka thiyenne,
            //JPA REPO (find all) -(extend)->  EmployeeRepository --> EmployeeServiceImpl

            //return employeeList; mehema daanna ba eka paara array ekak widihata return karanna

            //employee Entity eke thiyena Data EmployeeeDTO ekata dagannawa
            for (Employee employee : employeeList) {

                EmployeeDTO dto = new EmployeeDTO();
                dto.setEmployeeId(employee.getEmployeeId());
                dto.setFirstname(employee.getFirstname());
                dto.setLastname(employee.getLastname());
                dto.setAddress(employee.getAddress());
                dto.setJoinDate(employee.getJoinDate());

                //add karanna ona list eka response list ekata
                reponseList.add(dto);
            }

            log.info("getAllEmployee executed successfully");
            //response list ekata return karanna
            return reponseList;

        } catch (Exception e) {
            //catch eken eliyata yanne nathi wenna throw daanawa
            log.error("Error in getAllEmployee", e);
            throw new RuntimeException(e);
        }


    }

    @Override
    public EmployeeDTO getEmployeeDetails(long employeeId) {
        //data type eka wennne optional
        //employee kiyana table eke primary key ekak thiyenawada kiyala tham,ai findby id eken check karanne
        log.info("Execute method getEmployeeDetails() employeeId:{}", employeeId);
        try {
            //optional ekedi karanne data ekak thiyenawa nam denawa nathnam iwath wenaw
            Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

            //thawama data ekak thiyenawada nadda kiyala nathnam warning ekak enawa
            if (!optionalEmployee.isPresent())
                throw new RuntimeException("Sorry Related employee is not found");

            Employee employee = optionalEmployee.get();
            //methanin passe aniwaarenma dannawa employee eka athule data thinawa kila

            EmployeeDTO reponseData = new EmployeeDTO();
            reponseData.setEmployeeId(employee.getEmployeeId());
            reponseData.setFirstname(employee.getFirstname());
            reponseData.setLastname(employee.getLastname());
            reponseData.setAddress(employee.getAddress());
            reponseData.setJoinDate(employee.getJoinDate());

            return reponseData;


        } catch (Exception e) {
            log.error("Error in getEmployeeDetails", e);
            throw e;
            //throw = return + print trace
        }
        //primary key ekata adaal uniq record eka retrive karaganna puluwan, retrive ekedi adaala object eka denne na optional
        //kiyalka thamai denne ethanadi data ekak check karanawa api
    }

    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {
        log.info("Execute method updateEmployee() employeeDTO:{}",employeeDTO);

        try {
            Optional<Employee> optionalEmployee = employeeRepository.findById(employeeDTO.getEmployeeId());
            if(optionalEmployee.isEmpty())
                throw new RuntimeException("Sorry Related employee is not found");

            Employee employee = optionalEmployee.get();
            employee.setFirstname(employeeDTO.getFirstname());
            employee.setLastname(employeeDTO.getLastname());
            employee.setAddress(employeeDTO.getAddress());
            employee.setJoinDate(employeeDTO.getJoinDate());

            employeeRepository.save(employee);
            log.info("Employee updated successfully");

        } catch (Exception e) {
            log.error("Error in updateEmployee", e);
            throw e;
        }

    }

    @Override
    public void updateAddress(UpdateEmployeeDTO updateEmployeeDTO) {
        log.info("Execute method updateAddress() updateEmployeeDTO:{}",updateEmployeeDTO);

        try {
            Optional<Employee> optionalEmployee = employeeRepository.findById(updateEmployeeDTO.getEmployeeId());
            if(optionalEmployee.isEmpty())
                throw new RuntimeException("Sorry Related employee is not found");

            Employee employee = optionalEmployee.get();
            employee.setAddress(updateEmployeeDTO.getAddress());

            employeeRepository.save(employee);
            log.info("Employee Address updated successfully");

        } catch (Exception e) {
            log.error("Error in updateAddress", e);
            throw e;
        }
    }


    //Delete Employee (delete karanne na api delete karanawa kiyala karanne status eka inactive karana eka)
    @Override
    public void changeEmployeeStatus(long employeeId) {
        log.info("Execute method changeEmployeeStatus() employeeId:{}",employeeId);
        try {
            //front end eke thiyena id eka ganawa
            Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
           //ehema id ekak nathnam error ekak throw karanawa
            if(optionalEmployee.isEmpty())
                throw new RuntimeException("Sorry Related employee is not found");

            //Front end eken data eka ewanna ona na inactive karanna id eka thibbama athi
            Employee employee = optionalEmployee.get();
            employee.setEmployeeStatus(EmployeeStatus.INACTIVE);

            employeeRepository.save(employee);
            log.info("Employee status updated successfully");

        } catch (Exception e) {
            log.error("Error in getEmployeeDetails", e);
            throw e;
            //throw = return + print trace
        }
    }


}


