package com.freddxant.spring.playground.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthResDto implements Serializable {

    private String email;
    private String accessToken;

}
