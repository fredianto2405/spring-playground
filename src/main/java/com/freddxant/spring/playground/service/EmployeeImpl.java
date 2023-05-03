package com.freddxant.spring.playground.service;

import com.freddxant.spring.playground.model.entity.Employee;
import com.freddxant.spring.playground.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }

    @Override
    public Optional<Employee> findByEmployeeId(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee deleteByEmployeeId(Long id) {
        Optional<Employee> employee = findByEmployeeId(id);
        Employee employeeData = new Employee();

        if (employee.isPresent()) {
            employeeData = employee.get();
            employeeRepository.deleteById(id);
        }

        return employeeData;
    }

}
