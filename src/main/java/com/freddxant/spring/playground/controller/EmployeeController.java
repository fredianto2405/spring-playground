package com.freddxant.spring.playground.controller;

import com.freddxant.spring.playground.model.dto.EmployeeDto;
import com.freddxant.spring.playground.model.dto.ResponseDto;
import com.freddxant.spring.playground.model.entity.Employee;
import com.freddxant.spring.playground.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Operation(summary = "Find All Employee")
    @GetMapping(value = "/employee/findAllEmployee")
    @CrossOrigin(value = "*")
    public ResponseEntity<?> findAllEmployee() {
        ResponseDto responseDto = employeeService.findAllEmployee();
        return new ResponseEntity<>(responseDto, HttpStatus.resolve(responseDto.getCode()));
    }

    @Operation(summary = "Find by Employee Id")
    @GetMapping(value = "/employee/findByEmployeeId")
    @CrossOrigin(value = "*")
    public ResponseEntity<?> findByEmployeeId(@RequestParam Long id) {
        ResponseDto responseDto = employeeService.findByEmployeeId(id);
        return new ResponseEntity<>(responseDto, HttpStatus.resolve(responseDto.getCode()));
    }

    @Operation(summary = "Save Employee")
    @PostMapping(value = "/employee/saveEmployee")
    @CrossOrigin(value = "*")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setAddress(employeeDto.getAddress());
        employee.setGender(employeeDto.getGender());
        employee.setPosition(employeeDto.getPosition());

        ResponseDto responseDto = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(responseDto, HttpStatus.resolve(responseDto.getCode()));
    }

    @Operation(summary = "Update by Employee Id")
    @PutMapping(value = "/employee/updateEmployee/{id}")
    @CrossOrigin(value = "*")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setAddress(employeeDto.getAddress());
        employee.setGender(employeeDto.getGender());
        employee.setPosition(employeeDto.getPosition());

        ResponseDto responseDto = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(responseDto, HttpStatus.resolve(responseDto.getCode()));
    }

    @Operation(summary = "Delete by Employee Id")
    @DeleteMapping(value = "/employee/deleteEmployee/{id}")
    @CrossOrigin(value = "*")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        ResponseDto responseDto = employeeService.deleteByEmployeeId(id);
        return new ResponseEntity<>(responseDto, HttpStatus.resolve(responseDto.getCode()));
    }

}
