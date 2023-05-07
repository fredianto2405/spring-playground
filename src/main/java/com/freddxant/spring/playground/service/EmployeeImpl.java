package com.freddxant.spring.playground.service;

import com.freddxant.spring.playground.model.dto.EmployeeDto;
import com.freddxant.spring.playground.model.dto.ResponseDto;
import com.freddxant.spring.playground.model.entity.Employee;
import com.freddxant.spring.playground.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ResponseDto findAllEmployee() {
        ResponseDto responseDto = new ResponseDto();

        try {
            List<Employee> employeeList = employeeRepository.findAll();
            List<EmployeeDto> employeeDtoList = new ArrayList<>();

            for (Employee employee : employeeList) {
                EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
                employeeDtoList.add(employeeDto);
            }

            if (employeeDtoList.size() > 0) {
                responseDto.setSuccess(true);
                responseDto.setCode(200);
                responseDto.setMessage("Data found");
                responseDto.setData(employeeDtoList);
            } else {
                responseDto.setSuccess(true);
                responseDto.setCode(404);
                responseDto.setMessage("No data found");
                responseDto.setData(employeeDtoList);
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

    @Override
    public ResponseDto saveEmployeeJdbcTemplate(Employee employee) {
        ResponseDto responseDto = new ResponseDto();
        Connection connection = null;
        CallableStatement callableStatement = null;
        String sql = "";
        int numOfParam = 1;

        try {
            connection = jdbcTemplate.getDataSource().getConnection();

            // Oracle Database
            // connection.setAutoCommit(false);

            sql = "{ ? = call saveEmployee(?, ?, ?, ?, ?) }";
            callableStatement = connection.prepareCall(sql);

            callableStatement.registerOutParameter(numOfParam++, 2);
            callableStatement.setString(numOfParam++, employee.getFirstName());
            callableStatement.setString(numOfParam++, employee.getLastName());
            callableStatement.setString(numOfParam++, employee.getAddress());
            callableStatement.setString(numOfParam++, employee.getGender());
            callableStatement.setString(numOfParam++, employee.getPosition());
            callableStatement.execute();

            if (callableStatement.getInt(1) == 1) {
                responseDto.setSuccess(true);
                responseDto.setCode(200);
                responseDto.setMessage("Employee successfully saved");
                responseDto.setData(employee);
            } else {
                responseDto.setSuccess(false);
                responseDto.setCode(400);
                responseDto.setMessage("Can't save employee");
                responseDto.setData(employee);
            }

            callableStatement.close();
            connection.close();
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setCode(500);
            responseDto.setMessage(e.getMessage());
            responseDto.setData(null);
        }

        return responseDto;
    }

}
