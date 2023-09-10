package com.anton.microOne.service;

import com.anton.microOne.client.APIClient;
import com.anton.microOne.dto.MicroTwoDepartment;
import com.anton.microOne.dto.Response;
import com.anton.microOne.model.Contact;
import com.anton.microOne.model.Employee;
import com.anton.microOne.repo.ContactRepository;
import com.anton.microOne.repo.EmployeeRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    APIClient apiClient;

    //Create Employee
    public Employee createEmployee(Employee employee) {
        Contact contact = new Contact();
        System.out.println(employee);
        return employeeRepository.save(employee);
    }

    //Read Employee
    public List<Employee> getEmployee(){
        return employeeRepository.findAll();
    }

    //Update Employee
    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public boolean deleteEmployee(Long itemId) {
        if (employeeRepository.existsById(itemId)) {
            employeeRepository.deleteById(itemId);
            return true;
        } else {
            return false;
        }
    }

    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).get();
    }

    public Response getEmployeeByID(Long id) {
        Response response = new Response();
        Optional<Employee> getEmployee = employeeRepository.findById(id);
        if(getEmployee.isPresent()){
            Employee employee = getEmployee.get();
            MicroTwoDepartment microTwoDepartment = apiClient.getMicroTwoDepartmentById(employee.employeeId);
            response.setEmployee(employee);
            response.setMicroTwoDepartment(microTwoDepartment);
        }
        else {
            response.setMsg("Not found");
        }
        return response;
    }

}
