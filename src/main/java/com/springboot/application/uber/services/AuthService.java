package com.springboot.application.uber.services;

import com.springboot.application.uber.dto.DriverDto;
import com.springboot.application.uber.dto.OnboardDriverDto;
import com.springboot.application.uber.dto.SignupDto;
import com.springboot.application.uber.dto.UserDto;

public interface AuthService {

    String[] login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId, OnboardDriverDto onboardDriverDto);

    String refreshToken(String refreshToken);
}
