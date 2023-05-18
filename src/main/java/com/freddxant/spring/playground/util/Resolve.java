package com.freddxant.spring.playground.util;

import com.freddxant.spring.playground.model.entity.Employee;
import com.freddxant.spring.playground.model.entity.Position;
import com.freddxant.spring.playground.repository.EmployeeRepository;
import com.freddxant.spring.playground.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Resolve {

    private final PositionRepository positionRepository;

    private final EmployeeRepository employeeRepository;

    public Position resolvePosition(Long id) {
        Optional<Position> position = positionRepository.findById(id);
        if (position.isPresent()) {
            return position.get();
        } else {
            throw new RuntimeException("Position with id " + id + " not found");
        }
    }

    public Employee resolveEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RuntimeException("Employee with id " + id + " not found");
        }
    }

}
