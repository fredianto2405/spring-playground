package com.freddxant.spring.playground.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeDto implements Serializable {

    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private String position;

}
