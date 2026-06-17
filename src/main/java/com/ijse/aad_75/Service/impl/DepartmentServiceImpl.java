package com.ijse.aad_75.Service.impl;

import com.ijse.aad_75.Repository.DepartmentRepository;
import com.ijse.aad_75.Service.DepartmentService;
import com.ijse.aad_75.dto.DepartmentDTO;
import com.ijse.aad_75.dto.request.UpdateDepartmentDTO;
import com.ijse.aad_75.entity.Department;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    public final DepartmentRepository departmentRepository;

    @Override
    public void saveDepartment(DepartmentDTO departmentDTO) {
        try {
            log.info("Execute method saveDepartment");
           Department department = new Department();
           department.setDepartmentName(departmentDTO.getDepartmentName());
           department.setDepartmentLocation(departmentDTO.getDepartmentLocation());

           departmentRepository.save(department);
           log.info("Department saved successfully");

        }catch (Exception e){
            log.error("Error in saveDepartment",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DepartmentDTO> getAllDepartment(){
        try {
            log.info("Execute method getAllDepartment");
            List<DepartmentDTO> responsList = new ArrayList<>();
            List<Department> departmentList = departmentRepository.findAll();

            for (Department department : departmentList) {
                DepartmentDTO dto = new DepartmentDTO();
                dto.setDepartmentId(department.getDepartmentId());
                dto.setDepartmentName(department.getDepartmentName());
                dto.setDepartmentLocation(department.getDepartmentLocation());

                responsList.add(dto);

            }
            return responsList;

        } catch (Exception e) {
            log.error("Error in getAllDepartment",e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public DepartmentDTO getDepartmentDetails(long departmentId){
        log.info("Execute method getDepartmentDetails() departmentId:{}",departmentId);
        try{
            Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);

            if(optionalDepartment.isEmpty())
                throw new RuntimeException("Sorry Related Department is not found");

            Department department = optionalDepartment.get();

            DepartmentDTO responseData = new DepartmentDTO();
            responseData.setDepartmentId(department.getDepartmentId());
            responseData.setDepartmentName(department.getDepartmentName());
            responseData.setDepartmentLocation(department.getDepartmentLocation());
            return responseData;
        }catch (Exception e){
            log.error("Error in getDepartmentDetails",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateDepartment(DepartmentDTO departmentDTO) {
        log.info("Execute method updateDepartment() departmentDTO:{}",departmentDTO);

        try {
            //mulinma id ekata thiyena de[artment eka gannawa , using find by id
            Optional<Department> optionalDepartment = departmentRepository.findById(departmentDTO.getDepartmentId());

            if(optionalDepartment.isEmpty())
                throw new RuntimeException("Sorry Related Department is not found");

            Department department = optionalDepartment.get();

            System.out.println(department.getDepartmentId());

            //data tika set karanawa object ekata
            department.setDepartmentName(departmentDTO.getDepartmentName());
            department.setDepartmentLocation(departmentDTO.getDepartmentLocation());

            //object ekata gaththa data tika save karanawa
            departmentRepository.save(department);
            log.info("Department updated successfully");

            //save karana object eke primary key ekak nathnam update wena eka thamai wenne

        }catch (Exception e){
            log.error("Error in updateDepartment : "+e.getMessage());
            throw e;
        }
    }

    @Override
    public void updateLocation(UpdateDepartmentDTO updateDepartmentDTO) {
        log.info("Execute method updateLocation() updateDepartmentDTO:{}",updateDepartmentDTO);

      //front end eken ena id eka use karal;a optional depart ment ekak hadanawa
      //ehema ekak nathnam run time error ekak enawa, ekata solution ekak widihata api warning string ekak denawa
        try {
            Optional<Department> optionalDepartment = departmentRepository.findById(updateDepartmentDTO.getDepartmentId());
            if(optionalDepartment.isEmpty())
                throw new RuntimeException("Sorry Related Department is not found");

            Department department = optionalDepartment.get();

            department.setDepartmentLocation(updateDepartmentDTO.getDepartmentLocation());

            departmentRepository.save(department);
            log.info("Department Location updated successfully");


        }catch (Exception e){
            log.error("Error in updateLocation : "+e.getMessage());
            throw e;
        }
    }

    @Override
    public List<DepartmentDTO> filterDepartment(String departmentName, String departmentLocation) {
        log.info("Execute method filterDepartment() departmentName:{}", departmentName);
        try{
            //mulinma department name nullda balanna ona
//            if(departmentName ==null)
//                throw new RuntimeException("Department Name is missing");
            //filter api wala front end eken ewana data check laranne na


            //api repo ekata kiyanawa jpa eke inbulild funtion thiyenawa
            //apata puluwan apata awshya query customize karaganna
            //native sql widihata
            List<DepartmentDTO> responseList = new ArrayList<>();
            List<Department> departmentList = departmentRepository.filterDepartments(departmentName,departmentLocation);

            for (Department department : departmentList) {
                DepartmentDTO dto = new DepartmentDTO();
                dto.setDepartmentId(department.getDepartmentId());
                dto.setDepartmentName(department.getDepartmentName());
                dto.setDepartmentLocation(department.getDepartmentLocation());
                responseList.add(dto);
            }
            return responseList;

            } catch (Exception e){
                 log.error("Error in filterDepartment : "+e.getMessage());
              throw e;
        }
    }

//    @Override
//    public List<DepartmentDTO> filterDepartmentLocation(String departmentLocation) {
//        log.info("Execute method filterDepartmentLocation() departmentLocation:{}", departmentLocation);
//        try{
//            List<DepartmentDTO> departmentDTOList = new ArrayList<>();
//            List<Department> departmentList = departmentRepository.filterDepartmentLocation(departmentLocation);
//
//            for (Department department : departmentList) {
//                DepartmentDTO dto = new DepartmentDTO();
//                dto.setDepartmentId(department.getDepartmentId());
//                dto.setDepartmentName(department.getDepartmentName());
//                dto.setDepartmentLocation(department.getDepartmentLocation());
//                departmentDTOList.add(dto);
//                return departmentDTOList;
//            }
//            return departmentDTOList;
//
//        }catch (Exception e){
//            log.error("Error in getDepartmentDetails : "+ e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
//Filter Department Location eka filter department eka athulema haduwa


}
