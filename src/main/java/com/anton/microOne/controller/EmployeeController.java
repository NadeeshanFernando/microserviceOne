package com.anton.microOne.controller;

import com.anton.microOne.client.APIClient;
import com.anton.microOne.dto.ContactDto;
import com.anton.microOne.dto.EmployeeDto;
import com.anton.microOne.dto.Response;
import com.anton.microOne.dto.client.ClientResponse;
import com.anton.microOne.model.Contact;
import com.anton.microOne.model.Employee;
import com.anton.microOne.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/microOne/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    APIClient apiClient;

    @PostMapping
    public ResponseEntity<Response<Employee>> saveEmployee(@RequestBody EmployeeDto employeeDto){
        Response<Employee> response = employeeService.saveEmployee(employeeDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<Employee>>> getEmployee(){
        Response<List<Employee>> response = employeeService.getEmployee();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Employee>> getEmployeeById(@PathVariable Long id){
        Response<Employee> response = employeeService.getEmployeeById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Employee>> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long id){
        Response<Employee> response = employeeService.updateEmployee(employeeDto,id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
