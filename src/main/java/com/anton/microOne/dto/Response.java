package com.anton.microOne.dto;

import com.anton.microOne.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private MicroTwoDepartment microTwoDepartment;

    private Employee employee;

    private String msg;
}
