package com.freddxant.spring.playground.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class EmployeeDto implements Serializable {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String address;
    @NotNull
    private String gender;
    @NotNull
    private String position;

}
