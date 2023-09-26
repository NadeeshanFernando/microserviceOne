package com.anton.microOne.dto.client;

import com.anton.microOne.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author by nadeeshan_fdz
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse<M> {

    private MicroTwoDepartment microTwoDepartment;
    private Employee employee;
    private String msg;

}
