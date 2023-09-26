package com.anton.microOne.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import jakarta.persistence.*;

/**
 * @author by nadeeshan_fdz
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_Id")
//    @JsonIgnore
    public long contactId;

    @Column(name = "mobile")
    public String mobile;

    @Column(name = "remark")
    public String remark;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties("contactList")
    private Employee employee;

}
