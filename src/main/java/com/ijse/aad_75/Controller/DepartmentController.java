package com.ijse.aad_75.Controller;

import com.ijse.aad_75.Service.DepartmentService;
import com.ijse.aad_75.constant.CommonResponse;
import com.ijse.aad_75.dto.DepartmentDTO;
import com.ijse.aad_75.dto.request.UpdateDepartmentDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ijse.aad_75.constant.RespondMessage.SUCCESS;
import static com.ijse.aad_75.constant.ResponseCode.OPERATION_SUCCESS;

@RestController
@RequestMapping("v1/department")
public class DepartmentController {

    //construction injection
    private final DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public String saveDepartment(@RequestBody DepartmentDTO departmentDTO){
//        departmentService.saveDepartment(departmentDTO);
//        return "Department Saved...";
//    }
    public CommonResponse saveDepartment(@RequestBody DepartmentDTO departmentDTO){
        departmentService.saveDepartment(departmentDTO);
        return new CommonResponse(OPERATION_SUCCESS , SUCCESS);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<DepartmentDTO> getAllDepartment(){
//        return departmentService.getAllDepartment();
//    }
    public CommonResponse getAllDepartments(){
        List<DepartmentDTO> allDepartment = departmentService.getAllDepartment();
        return new CommonResponse(OPERATION_SUCCESS,allDepartment ,SUCCESS);
    }

    //get by id
    @GetMapping(value = "/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getDepartmentDetails(@PathVariable long departmentId){

        DepartmentDTO departmentDetails = departmentService.getDepartmentDetails(departmentId);

        return new CommonResponse(OPERATION_SUCCESS,departmentDetails,SUCCESS);
    }

    //update
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateDepartment(@RequestBody DepartmentDTO departmentDTO){
        departmentService.updateDepartment(departmentDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS);
        //body ekak daanne na methanadi api front end eke result ekak penwanne nathi nisa
    }

    //update location
    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateLocations(@RequestBody UpdateDepartmentDTO updateDepartmentDTO){
        departmentService.updateLocation(updateDepartmentDTO);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS);
    }


    //filter
    //    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    //me wage dekak thibbama ambigues error enna puluwan eka nisa
    //specifie karana wenawa
    @GetMapping(value = "filter", produces = MediaType.APPLICATION_JSON_VALUE)

    //value ekata daanna ona key eka
    //req eken adahas wenne me api ekata me data eka aniwaaren ewanna onada nadd akiyana eka
    //required = false - eka thiyana data eka aniwaaren ewanna ona na
    //dat eka yawann eekaka witharaine e date eka ewannath ona saha null kiyala ewannath ona , false karaama front end eken ewuwath ekai nathath ekai m, eka frint end poaththata lesiy
    public CommonResponse filterDepartments(
            @RequestParam(value = "departmentName",required = false) String departmentName,
            @RequestParam(value = "departmentLocation",required = false) String departmentLocation){

        List<DepartmentDTO> departmentDTOS = departmentService.filterDepartment(departmentName, departmentLocation);

        return new CommonResponse(OPERATION_SUCCESS,departmentDTOS,SUCCESS);
    }

//    @GetMapping(value = "filterLocation", produces = MediaType.APPLICATION_JSON_VALUE)
//    public CommonResponse filterDepartmentLocation(@RequestParam(value = "departmentLocation",required = false) String departmentLocation){
//        List<DepartmentDTO> departmentDTOS = departmentService.filterDepartmentLocation(departmentLocation);
//        return new CommonResponse(OPERATION_SUCCESS,departmentDTOS,SUCCESS);
//    }
    //Department Location eka filter karana eka filterDepartment() ekenma karagaththa


}
