package com.anton.microOne.dto;

import lombok.*;

/**
 * @author by nadeeshan_fdz
 */
@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class EmployeeDto {

    public long employeeId;
    public String empName;
    public String empPassword;
    public int empAge;
    public double empSalary;
    public long deptId;
}
