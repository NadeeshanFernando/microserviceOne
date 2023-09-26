package com.anton.microOne.repo;

import com.anton.microOne.model.Contact;
import com.anton.microOne.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author by nadeeshan_fdz
 */

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    boolean existsByEmployeeAndMobile(Employee employee, String mobile);

    boolean existsByEmployeeAndMobileAndContactIdNot(Employee employee, String mobile, Long contactId);
}
