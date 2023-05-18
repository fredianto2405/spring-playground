package com.freddxant.spring.playground.service;

import com.freddxant.spring.playground.model.dto.EmployeeReqDto;
import com.freddxant.spring.playground.model.dto.EmployeeResDto;
import com.freddxant.spring.playground.model.dto.ResponseDto;
import com.freddxant.spring.playground.model.entity.Employee;
import com.freddxant.spring.playground.model.entity.Position;
import com.freddxant.spring.playground.repository.EmployeeRepository;
import com.freddxant.spring.playground.util.Resolve;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final Resolve resolve;

    @Override
    public ResponseDto findAllEmployee() {
        ResponseDto responseDto = new ResponseDto();

        try {
            List<Employee> employeeList = employeeRepository.findAll();
            List<EmployeeResDto> employeeResDtoList = new ArrayList<>();

            for (Employee employee : employeeList) {
                EmployeeResDto employeeResDto = modelMapper.map(employee, EmployeeResDto.class);
                employeeResDtoList.add(employeeResDto);
            }

            responseDto.setSuccess(true);
            responseDto.setCode(200);
            responseDto.setData(employeeResDtoList);

            if (employeeResDtoList.size() > 0) {
                responseDto.setMessage("Data found");
            } else {
                responseDto.setMessage("No data found");
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
            Employee employee = resolve.resolveEmployee(id);
            EmployeeResDto employeeResDto = modelMapper.map(employee, EmployeeResDto.class);

            responseDto.setSuccess(true);
            responseDto.setCode(200);

            if (employeeResDto != null) {
                responseDto.setMessage("Data found");
                responseDto.setData(employeeResDto);
            } else {
                responseDto.setMessage("No data found");
                responseDto.setData(null);
            }
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setCode(400);
            responseDto.setMessage(e.getMessage());
            responseDto.setData(null);
        }

        return responseDto;
    }

    @Override
    public ResponseDto saveEmployee(EmployeeReqDto employeeReqDto) {
        ResponseDto responseDto = new ResponseDto();

        try {
            Position position = resolve.resolvePosition(employeeReqDto.getPositionId());
            Employee employee = modelMapper.map(employeeReqDto, Employee.class);
            employee.setPosition(position);
            employeeRepository.save(employee);

            responseDto.setSuccess(true);
            responseDto.setCode(200);
            responseDto.setMessage("Employee successfully saved");
            responseDto.setData(employee);
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setCode(400);
            responseDto.setMessage(e.getMessage());
            responseDto.setData(null);
        }

        return responseDto;
    }

    @Override
    public ResponseDto deleteByEmployeeId(Long id) {
        ResponseDto responseDto = new ResponseDto();

        try {
            Employee employee = resolve.resolveEmployee(id);

            if (employee != null) {
                try {
                    employeeRepository.deleteById(id);
                    responseDto.setSuccess(true);
                    responseDto.setCode(200);
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
                responseDto.setCode(200);
                responseDto.setMessage("No data found with id " + id);
                responseDto.setData(null);
            }
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setCode(400);
            responseDto.setMessage(e.getMessage());
            responseDto.setData(null);
        }

        return responseDto;
    }

    @Override
    public ResponseDto updateEmployee(Long id, EmployeeReqDto employeeReqDto) {
        ResponseDto responseDto = new ResponseDto();

        try {
            Position position = resolve.resolvePosition(employeeReqDto.getPositionId());
            Employee employee = modelMapper.map(employeeReqDto, Employee.class);
            employee.setId(id);
            employee.setPosition(position);
            employeeRepository.save(employee);

            responseDto.setSuccess(true);
            responseDto.setCode(200);
            responseDto.setMessage("Employee successfully saved");
            responseDto.setData(employee);
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setCode(400);
            responseDto.setMessage(e.getMessage());
            responseDto.setData(null);
        }

        return responseDto;
    }

}
