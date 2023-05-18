package com.freddxant.spring.playground.service;

import com.freddxant.spring.playground.model.dto.EmployeeReqDto;
import com.freddxant.spring.playground.model.dto.ResponseDto;

public interface EmployeeService {

    ResponseDto findAllEmployee();
    ResponseDto findByEmployeeId(Long id);
    ResponseDto saveEmployee(EmployeeReqDto employeeReqDto);
    ResponseDto deleteByEmployeeId(Long id);
    ResponseDto updateEmployee(Long id, EmployeeReqDto employeeReqDto);

}
