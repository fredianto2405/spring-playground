package com.freddxant.spring.playground.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class EmployeeDto implements Serializable {

    @NotNull
    @Schema(name = "firstName", example = "John")
    private String firstName;
    @NotNull
    @Schema(name = "lastName", example = "Doe")
    private String lastName;
    @NotNull
    @Schema(name = "address", example = "Tangerang, Banten")
    private String address;
    @NotNull
    @Schema(name = "gender", example = "Male")
    private String gender;
    @NotNull
    @Schema(name = "position", example = "Software Engineer")
    private String position;

}
