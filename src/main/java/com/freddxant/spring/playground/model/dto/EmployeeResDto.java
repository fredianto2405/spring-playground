package com.freddxant.spring.playground.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeResDto implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private Long positionId;
    private String positionName;

}
