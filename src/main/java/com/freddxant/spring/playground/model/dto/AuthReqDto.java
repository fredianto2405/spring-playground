package com.freddxant.spring.playground.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AuthReqDto implements Serializable {

    @NotNull
    private String email;
    @NotNull
    private String password;

}
