package com.anton.microOne.client;

import com.anton.microOne.dto.client.MicroTwoDepartment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "MICRO-TWO", url = "localhost:8082/microTwo")
public interface APIClient {

    @GetMapping(value = "/getDepartment/{id}")
    MicroTwoDepartment getMicroTwoDepartmentById(@PathVariable ("id") Long id);
}
