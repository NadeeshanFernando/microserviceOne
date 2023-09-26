package com.anton.microOne.repo;

import com.anton.microOne.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author by nadeeshan_fdz
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByEmpName(String empName);

    boolean existsByEmpNameAndEmployeeIdNot(String empName, long employeeId);

    //    @Query("SELECT e from Employee e left join e.employee e where e.id=?1")
//    List<Employee> findByParentEntityId(Long id);
}
