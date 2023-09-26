package com.anton.microOne.service;

import com.anton.microOne.client.APIClient;
import com.anton.microOne.dto.Response;
import com.anton.microOne.dto.client.ClientResponse;
import com.anton.microOne.dto.client.MicroTwoDepartment;
import com.anton.microOne.model.Employee;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by nadeeshan_fdz
 */

@Service
public class ClientService {

    @Autowired
    APIClient apiClient;

    public MicroTwoDepartment getMicroTwoDepartmentById(Long id) {
        try {
            MicroTwoDepartment microTwoDepartment = apiClient.getMicroTwoDepartmentById(id);

            if (microTwoDepartment == null) {
                System.out.println("Department not found.");
            } else {
                System.out.println("Department found: " + microTwoDepartment);
            }

            return microTwoDepartment;
        } catch (FeignException.NotFound e) {
            // Handle the 404 response here
            System.err.println("Department with ID " + id + " not found.");
        } catch (FeignException e) {
            // Handle other FeignExceptions if needed
            System.err.println("FeignException: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions if needed
            System.err.println("Exception: " + e.getMessage());
        }

        return null;
    }


}
