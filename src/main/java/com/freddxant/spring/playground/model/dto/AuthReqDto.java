package com.freddxant.spring.playground.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AuthReqDto implements Serializable {

    @NotNull
    @Schema(name = "email", example = "user@email.com")
    private String email;
    @NotNull
    @Schema(name = "password", example = "12345678")
    private String password;

}
