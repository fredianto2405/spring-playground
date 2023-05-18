package com.freddxant.spring.playground.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GithubUserDto implements Serializable {

    private String name;
    private String email;
    private String blog;
    private String location;
    private String html_url;

}
