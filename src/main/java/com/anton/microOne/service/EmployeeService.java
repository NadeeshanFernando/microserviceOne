package com.anton.microOne.service;

import com.anton.microOne.client.APIClient;
import com.anton.microOne.dto.EmployeeDto;
import com.anton.microOne.dto.Response;
import com.anton.microOne.model.Employee;
import com.anton.microOne.repo.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author by nadeeshan_fdz
 */

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    APIClient apiClient;


//    public ClientResponse getEmployeeByID(Long id) {
//        ClientResponse response = new ClientResponse();
//        Optional<Employee> getEmployee = employeeRepository.findById(id);
//        if(getEmployee.isPresent()){
//            Employee employee = getEmployee.get();
//            MicroTwoDepartment microTwoDepartment = apiClient.getMicroTwoDepartmentById(employee.employeeId);
//            response.setEmployee(employee);
//            response.setMicroTwoDepartment(microTwoDepartment);
//        }
//        else {
//            response.setMsg("Not found");
//        }
//        return response;
//    }

    public Response<Employee> saveEmployee(EmployeeDto employeeDto) {
        log.info("Saving Employee");
        Response<Employee> response = new Response<>();

        if(!employeeRepository.existsByEmpName(employeeDto.getEmpName())){
            Employee employee = new Employee();
            employee.setEmpName(employeeDto.getEmpName());
            employee.setEmpPassword(employeeDto.getEmpPassword());
            employee.setEmpAge(employeeDto.getEmpAge());
            employee.setEmpSalary(employeeDto.getEmpSalary());
            employee.setDeptId(employeeDto.getDeptId());
            employeeRepository.save(employee);

            response.setData(employee);
            response.setStatus(HttpStatus.OK.value());
            response.setMsg("New Employee Saved");
            log.info("New Employee Saved");
        }
        else{
            response.setData(null);
            response.setStatus(HttpStatus.CONFLICT.value());
            response.setMsg("Employee Already Exist");
            log.info("Employee Already Exist");
        }
        return response;
    }

    public Response<List<Employee>> getEmployee() {
        log.info("Get Employee");
        Response<List<Employee>> response = new Response<>();

        List<Employee> employeeList = employeeRepository.findAll();
        if(employeeList.isEmpty()){
            response.setData(null);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMsg("Empty Employee List");
            log.info("Empty Employee List");
        }
        else{
            response.setData(employeeList);
            response.setStatus(HttpStatus.OK.value());
            response.setMsg("Retrieved Employee List");
            log.info("Retrieved Employee List");
        }
        return response;
    }

    public Response<Employee> getEmployeeById(Long id) {
        log.info("Get Employee By Id");
        Response<Employee> response = new Response<>();

        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isEmpty()){
            response.setData(null);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMsg("Employee Not Exist");
            log.info("Employee Not Exist");
        }
        else{
            response.setData(employeeOptional);
            response.setStatus(HttpStatus.OK.value());
            response.setMsg("Retrieved Employee");
            log.info("Retrieved Employee");
        }
        return response;
    }

    public Response<Employee> updateEmployee(EmployeeDto employeeDto, Long id) {
        log.info("Update Employee");
        Response<Employee> response = new Response<>();

        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(employeeOptional.isPresent()){
            Employee currentEmployee = employeeOptional.get();

//            check if the updated employee name is unique (excluding the current employee)
            boolean unique = !employeeRepository.existsByEmpNameAndEmployeeIdNot(employeeDto.getEmpName(),currentEmployee.getEmployeeId());

            if(unique){
                currentEmployee.setEmpName(employeeDto.getEmpName());
                currentEmployee.setEmpPassword(employeeDto.getEmpPassword());
                currentEmployee.setEmpAge(employeeDto.getEmpAge());
                currentEmployee.setEmpSalary(employeeDto.getEmpSalary());
                currentEmployee.setDeptId(employeeDto.getDeptId());
                employeeRepository.save(currentEmployee);

                response.setData(currentEmployee);
                response.setStatus(HttpStatus.OK.value());
                response.setMsg("Employee Updated");
                log.info("Employee Updated");
            }
            else{
                response.setData(null);
                response.setStatus(HttpStatus.CONFLICT.value());
                response.setMsg("Employee Name Already Exist");
                log.info("Employee Name Already Exist");
            }
        }
        else{
            response.setData(null);
            response.setStatus(HttpStatus.CONFLICT.value());
            response.setMsg("Employee Id Not Exist");
            log.info("Employee Id Not Exist");
        }
        return response;
    }
}
