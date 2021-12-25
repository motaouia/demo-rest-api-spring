package org.medmota.demorest.payroll.repositories;

import org.medmota.demorest.payroll.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
