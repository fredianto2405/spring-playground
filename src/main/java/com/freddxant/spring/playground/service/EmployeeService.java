package com.freddxant.spring.playground.service;

import com.freddxant.spring.playground.model.dto.ResponseDto;
import com.freddxant.spring.playground.model.entity.Employee;

public interface EmployeeService {

    ResponseDto findAllEmployee();
    ResponseDto findByEmployeeId(Long id);
    ResponseDto saveEmployee(Employee employee);
    ResponseDto deleteByEmployeeId(Long id);

}
