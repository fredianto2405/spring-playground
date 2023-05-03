package com.freddxant.spring.playground.service;

import com.freddxant.spring.playground.model.dto.EmployeeDto;
import com.freddxant.spring.playground.model.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAllEmployee();
    Optional<Employee> findByEmployeeId(Long id);
    Employee saveEmployee(Employee employee);
    Employee deleteByEmployeeId(Long id);

}
