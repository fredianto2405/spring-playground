package com.freddxant.spring.playground.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseDto<T> implements Serializable {

    private Boolean success;
    private String message;
    private T data;

}
