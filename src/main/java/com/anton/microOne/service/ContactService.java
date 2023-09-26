package com.anton.microOne.service;

import com.anton.microOne.dto.ContactDto;
import com.anton.microOne.dto.Response;
import com.anton.microOne.model.Contact;
import com.anton.microOne.model.Employee;
import com.anton.microOne.repo.ContactRepository;
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
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public Response<Contact> saveContact(ContactDto contactDto) {
        log.info("Saving Contact");
        Response<Contact> response = new Response<>();

        Optional<Employee> employeeOptional = employeeRepository.findById(contactDto.employeeId);
        if(employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            if(!contactRepository.existsByEmployeeAndMobile(employee,contactDto.mobile)){
                Contact contact = new Contact();
                contact.setMobile(contactDto.getMobile());
                contact.setRemark(contactDto.getRemark());
                contact.setEmployee(employee);
                contactRepository.save(contact);

                response.setData(contact);
                response.setStatus(HttpStatus.OK.value());
                response.setMsg("New Contact Saved");
                log.info("New Contact Saved");
            }
            else{
                response.setData(null);
                response.setStatus(HttpStatus.CONFLICT.value());
                response.setMsg("Contact Already Exist Under Employee Id");
                log.info("Contact Already Exist Under Employee Id");
            }
        }
        else{
            response.setData(null);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMsg("Employee Id Not Exist");
            log.info("Employee Id Not Exist");
        }
        return response;
    }

    public Response<List<Contact>> getContact() {
        log.info("Get Contact");
        Response<List<Contact>> response = new Response<>();

        List<Contact> contactList = contactRepository.findAll();
        if(contactList.isEmpty()){
            response.setData(null);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMsg("Empty Contact List");
            log.info("Empty Contact List");
        }
        else{
            response.setData(contactList);
            response.setStatus(HttpStatus.OK.value());
            response.setMsg("Retrieved Contact List");
            log.info("Retrieved Contact List");
        }
        return response;
    }

    public Response<Contact> getContactById(Long id) {
        log.info("Get Contact By Id");
        Response<Contact> response = new Response<>();

        Optional<Contact> contactOptional = contactRepository.findById(id);
        if(contactOptional.isEmpty()){
            response.setData(null);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMsg("Contact Not Exist");
            log.info("Contact Not Exist");
        }
        else{
            response.setData(contactOptional);
            response.setStatus(HttpStatus.OK.value());
            response.setMsg("Retrieved Contact");
            log.info("Retrieved Contact");
        }
        return response;
    }

    public Response<Contact> updateContact(ContactDto contactDto, Long id) {
        log.info("Update Contact");
        Response<Contact> response = new Response<>();

        Optional<Contact> contactOptional = contactRepository.findById(id);

        if(contactOptional.isPresent()){
            Contact currenContact = contactOptional.get();
            Optional<Employee> employeeOptional = employeeRepository.findById(contactDto.employeeId);

            if(employeeOptional.isPresent()){
                Employee employee = employeeOptional.get();

//                check if the updated contact number is unique (excluding the current contact number under employee id)
                boolean unique = !contactRepository.existsByEmployeeAndMobileAndContactIdNot(employee,contactDto.getMobile(),id);
                if(unique){
                    currenContact.setMobile(contactDto.getMobile());
                    currenContact.setRemark(contactDto.getRemark());
                    currenContact.setEmployee(employee);
                    contactRepository.save(currenContact);

                    response.setData(currenContact);
                    response.setStatus(HttpStatus.OK.value());
                    response.setMsg("Contact Updated");
                    log.info("Contact Updated");
                }
                else{
                    response.setData(null);
                    response.setStatus(HttpStatus.OK.value());
                    response.setMsg("Contact Already Exist Under Employee Id");
                    log.info("Contact Already Exist Under Employee Id");
                }
            }
            else{
                response.setData(null);
                response.setStatus(HttpStatus.NOT_FOUND.value());
                response.setMsg("Employee Id Not Exist");
                log.info("Employee Id Not Exist");
            }
        }
        else{
            response.setData(null);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMsg("Contact Not Exist");
            log.info("Contact Not Exist");
        }
        return response;
    }
}
