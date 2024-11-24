package com.springboot.application.uber.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
