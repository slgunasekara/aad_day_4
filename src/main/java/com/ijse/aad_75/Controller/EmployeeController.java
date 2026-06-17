package com.ijse.aad_75.Controller;

import com.ijse.aad_75.Service.EmployeeService;
import com.ijse.aad_75.constant.CommonResponse;
import com.ijse.aad_75.dto.EmployeeDTO;
import com.ijse.aad_75.dto.request.UpdateEmployeeDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ijse.aad_75.constant.RespondMessage.SUCCESS;
import static com.ijse.aad_75.constant.ResponseCode.OPERATION_SUCCESS;

//@Controller - mark as a controller
//@ResponseBody - return the response as a json
@RestController
@RequestMapping("v1/employee")
public class EmployeeController {

    //construction injection
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE) //post mappingf anotation ekata json string karanna kiyanawa
    ///front end eken ena data walata samana karanne
//    public String saveEmployee(@RequestBody EmployeeDTO employeeDTO){
      ///api eka call unaama employee kenekwa save karamu
      ///using dependancio injection - constructor
//        employeeService.saveEmployee(employeeDTO);
//        return "Employee Saved..." ;
//    }
    public CommonResponse saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.saveEmployee(employeeDTO);
        return new CommonResponse(OPERATION_SUCCESS , SUCCESS);
    }

    //all get
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<EmployeeDTO> getAllEmployee(){
//        return employeeService.getAllEmployee();
//    }
    public CommonResponse getAllEmployees(){
        List<EmployeeDTO> allEmployee = employeeService.getAllEmployee();
        return new CommonResponse(OPERATION_SUCCESS,allEmployee ,SUCCESS);
    }

    //get by id
    //api ekak specifie karamu
    @GetMapping(value = "/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getEmployeeDetails(@PathVariable long employeeId){

        EmployeeDTO employeeDetails = employeeService.getEmployeeDetails(employeeId);

        return new CommonResponse(OPERATION_SUCCESS,employeeDetails,SUCCESS);
    }

    //update
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.updateEmployee(employeeDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS);
    }

    //update address
    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateAddress(@RequestBody UpdateEmployeeDTO updateEmployeeDTO){
        employeeService.updateAddress(updateEmployeeDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS);
    }

    //delete
    @DeleteMapping(value = "/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse changeEmployeeStatus(@PathVariable long employeeId){
        employeeService.changeEmployeeStatus(employeeId);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS);
    }








    //ganna puluwan hodama kramaya path variable
    //nathnam json object ekak widihata hadanna ona eka tikak complex



}
