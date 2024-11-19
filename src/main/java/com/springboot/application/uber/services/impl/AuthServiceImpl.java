package com.springboot.application.uber.services.impl;

import com.springboot.application.uber.dto.DriverDto;
import com.springboot.application.uber.dto.SignupDto;
import com.springboot.application.uber.dto.UserDto;
import com.springboot.application.uber.entities.User;
import com.springboot.application.uber.entities.enums.Role;
import com.springboot.application.uber.exceptions.RuntimeConflictException;
import com.springboot.application.uber.repositories.UserRepository;
import com.springboot.application.uber.services.AuthService;
import com.springboot.application.uber.services.RiderService;
import com.springboot.application.uber.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class
AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RiderService riderService;
    private final WalletService walletService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if(user != null)
                throw new RuntimeConflictException("Cannot signup, User already exists with email "+signupDto.getEmail());

        User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

//        create user related entities
        riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
