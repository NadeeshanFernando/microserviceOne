package com.anton.microOne.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * @author by nadeeshan_fdz
 */

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
@Entity
@ToString
@Table (name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
//    @JsonIgnore
    public long employeeId;

    @Column(name = "empName")
    public String empName;

    @Column(name = "empPassword")
    public String empPassword;

    @Column(name = "empAge")
    public int empAge;

    @Column(name = "empSalary")
    public double empSalary;

    @Column(name = "department_Id")
    public long deptId;

    @OneToMany(mappedBy = "employee")
    @JsonIgnoreProperties("employee")
    private Set<Contact> contactList;

}
