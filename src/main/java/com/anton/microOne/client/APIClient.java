package com.anton.microOne.client;

import com.anton.microOne.dto.Response;
import com.anton.microOne.dto.client.ClientResponse;
import com.anton.microOne.dto.client.MicroTwoDepartment;
import com.anton.microOne.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author by nadeeshan_fdz
 */

@FeignClient(value = "MICRO-TWO", url = "${microTwo.url}")
public interface APIClient {

    @GetMapping(value = "/department/{id}")
    MicroTwoDepartment getMicroTwoDepartmentById(@PathVariable ("id") Long id);

}
