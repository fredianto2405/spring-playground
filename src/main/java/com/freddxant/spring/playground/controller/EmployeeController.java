package com.freddxant.spring.playground.controller;

import com.freddxant.spring.playground.model.dto.EmployeeDto;
import com.freddxant.spring.playground.model.dto.ResponseDto;
import com.freddxant.spring.playground.model.entity.Employee;
import com.freddxant.spring.playground.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/employee/")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "findAllEmployee")
    @CrossOrigin(value = "*")
    public ResponseEntity<?> findAllEmployee() {
        ResponseDto responseDto = new ResponseDto();

        try {
            List<Employee> employeeList = employeeService.findAllEmployee();
            responseDto.setSuccess(true);
            responseDto.setMessage(employeeList.size() > 0 ? "OK" : "NOT_FOUND");
            responseDto.setData(employeeList);
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setMessage(e.getMessage());
            responseDto.setData(null);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "findByEmployeeId")
    @CrossOrigin(value = "*")
    public ResponseEntity<?> findByEmployeeId(@RequestParam Long id) {
        ResponseDto responseDto = new ResponseDto();

        try {
            Optional<Employee> employee = employeeService.findByEmployeeId(id);
            responseDto.setSuccess(true);
            responseDto.setMessage(employee.isPresent() ? "OK" : "NOT_FOUND");
            responseDto.setData(employee);
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setMessage(e.getMessage());
            responseDto.setData(null);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping(value = "saveEmployee")
    @CrossOrigin(value = "*")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        ResponseDto responseDto = new ResponseDto();

        try {
            Employee employee = new Employee();
            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setAddress(employeeDto.getAddress());
            employee.setGender(employeeDto.getGender());
            employee.setPosition(employeeDto.getPosition());

            Employee employeeData = employeeService.saveEmployee(employee);

            responseDto.setSuccess(true);
            responseDto.setMessage(employeeData.getId() != null ? "Employee successfully saved" : "Can't save employee");
            responseDto.setData(employeeData);
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setMessage(e.getMessage());
            responseDto.setData(null);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping(value = "updateEmployee/{id}")
    @CrossOrigin(value = "*")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        ResponseDto responseDto = new ResponseDto();

        try {
            Employee employee = new Employee();
            employee.setId(id);
            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setAddress(employeeDto.getAddress());
            employee.setGender(employeeDto.getGender());
            employee.setPosition(employeeDto.getPosition());

            Employee employeeData = employeeService.saveEmployee(employee);

            responseDto.setSuccess(true);
            responseDto.setMessage(employeeData.getId() != null ? "Employee successfully updated" : "Can't update employee");
            responseDto.setData(employeeData);
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setMessage(e.getMessage());
            responseDto.setData(null);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "deleteEmployee/{id}")
    @CrossOrigin(value = "*")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();

        try {
            Employee employeeData = employeeService.deleteByEmployeeId(id);
            responseDto.setSuccess(true);
            responseDto.setMessage(employeeData.getId() != null ? "Employee successfully deleted" : "Employee data not found");
            responseDto.setData(employeeData.getId() != null ? employeeData : null);
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setMessage(e.getMessage());
            responseDto.setData(null);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
