package com.anton.microOne.controller;

import com.anton.microOne.client.APIClient;
import com.anton.microOne.dto.Response;
import com.anton.microOne.model.Employee;
import com.anton.microOne.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/microOne")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    APIClient apiClient;

    //Add Employee
    @PostMapping(path="/addEmployee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

    //Get Employee
    @GetMapping (path = "/getEmployee")
    public ResponseEntity<List<Employee>> getEmployee(){
        List<Employee> getEmployee = employeeService.getEmployee();
        if(getEmployee != null){
            return ResponseEntity.ok(getEmployee);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping (path = "/getEmployee/{id}")
    public ResponseEntity<Response> getEmployeeByID(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getEmployeeByID(id));
    }

    //Update Employee
    @PutMapping (path = "/putEmployee/{id}")
    public ResponseEntity<?>updateEmployee (@RequestBody Employee employee, @PathVariable long id){
        Optional<Employee> findEmployee = Optional.ofNullable(employeeService.getEmployeeById(id));
        if(findEmployee.isPresent()){
            Employee updateEmployee = findEmployee.get();
            updateEmployee.setEmpName(employee.getEmpName());
            updateEmployee.setEmpAge(employee.getEmpAge());
            updateEmployee.setEmpSalary(employee.getEmpSalary());
            return new ResponseEntity<>(employeeService.updateEmployee(updateEmployee), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Delete Employee
    @DeleteMapping(path = "/deleteEmployee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long id){
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
