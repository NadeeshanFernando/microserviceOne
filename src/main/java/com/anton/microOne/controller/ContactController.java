package com.anton.microOne.controller;

import com.anton.microOne.dto.ContactDto;
import com.anton.microOne.dto.Response;
import com.anton.microOne.model.Contact;
import com.anton.microOne.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author by nadeeshan_fdz
 */

@RestController
@RequestMapping("/microOne/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping
    public ResponseEntity<Response<Contact>> saveContact(@RequestBody ContactDto contactDto){
        Response<Contact> response = contactService.saveContact(contactDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<Contact>>> getContact(){
        Response<List<Contact>> response = contactService.getContact();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Contact>> getContactById(@PathVariable Long id){
        Response<Contact> response = contactService.getContactById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Contact>> updateContact(@RequestBody ContactDto contactDto, @PathVariable Long id){
        Response<Contact> response = contactService.updateContact(contactDto,id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
