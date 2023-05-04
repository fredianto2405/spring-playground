package com.freddxant.spring.playground.service;

import com.freddxant.spring.playground.model.dto.ResponseDto;
import com.freddxant.spring.playground.model.entity.Employee;
import com.freddxant.spring.playground.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public ResponseDto findAllEmployee() {
        ResponseDto responseDto = new ResponseDto();
        List<Employee> employeeList = new ArrayList<>();

        try {
            employeeList = employeeRepository.findAll();

            if (employeeList.size() > 0) {
                responseDto.setSuccess(true);
                responseDto.setCode(200);
                responseDto.setMessage("Data found");
                responseDto.setData(employeeList);
            } else {
                responseDto.setSuccess(true);
                responseDto.setCode(404);
                responseDto.setMessage("No data found");
                responseDto.setData(employeeList);
            }
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setCode(500);
            responseDto.setMessage(e.getMessage());
            responseDto.setData(null);
        }

        return responseDto;
    }

    @Override
    public ResponseDto findByEmployeeId(Long id) {
        ResponseDto responseDto = new ResponseDto();

        try {
            Optional<Employee> employee = employeeRepository.findById(id);

            if (employee.isPresent()) {
                responseDto.setSuccess(true);
                responseDto.setCode(200);
                responseDto.setMessage("Data found");
                responseDto.setData(employee);
            } else {
                responseDto.setSuccess(true);
                responseDto.setCode(404);
                responseDto.setMessage("No data found");
                responseDto.setData(null);
            }
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setCode(500);
            responseDto.setMessage(e.getMessage());
            responseDto.setData(null);
        }


        return responseDto;
    }

    @Override
    public ResponseDto saveEmployee(Employee employee) {
        ResponseDto responseDto = new ResponseDto();

        try {
            employeeRepository.save(employee);
            responseDto.setSuccess(true);
            responseDto.setCode(200);
            responseDto.setMessage("Employee successfully saved");
            responseDto.setData(employee);
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setCode(500);
            responseDto.setMessage(e.getMessage());
            responseDto.setData(null);
        }

        return responseDto;
    }

    @Override
    public ResponseDto deleteByEmployeeId(Long id) {
        ResponseDto responseDto = new ResponseDto();

        try {
            Optional<Employee> employee = employeeRepository.findById(id);

            if (employee.isPresent()) {
                try {
                    employeeRepository.deleteById(id);
                    responseDto.setSuccess(true);
                    responseDto.setCode(204);
                    responseDto.setMessage("Employee successfully deleted");
                    responseDto.setData(employee);
                } catch (Exception e) {
                    responseDto.setSuccess(false);
                    responseDto.setCode(500);
                    responseDto.setMessage(e.getMessage());
                    responseDto.setData(null);
                }
            } else {
                responseDto.setSuccess(true);
                responseDto.setCode(404);
                responseDto.setMessage("No data found with id " + id);
                responseDto.setData(null);
            }
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setCode(500);
            responseDto.setMessage(e.getMessage());
            responseDto.setData(null);
        }

        return responseDto;
    }

}
