package com.springboot.application.uber.services.impl;

import com.springboot.application.uber.dto.DriverDto;
import com.springboot.application.uber.dto.OnboardDriverDto;
import com.springboot.application.uber.dto.SignupDto;
import com.springboot.application.uber.dto.UserDto;
import com.springboot.application.uber.entities.Driver;
import com.springboot.application.uber.entities.User;
import com.springboot.application.uber.entities.enums.Role;
import com.springboot.application.uber.exceptions.ResourceNotFoundException;
import com.springboot.application.uber.exceptions.RuntimeConflictException;
import com.springboot.application.uber.repositories.UserRepository;
import com.springboot.application.uber.security.JWTService;
import com.springboot.application.uber.services.AuthService;
import com.springboot.application.uber.services.DriverService;
import com.springboot.application.uber.services.RiderService;
import com.springboot.application.uber.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public String[] login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new String[]{accessToken, refreshToken};
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if(user != null)
                throw new RuntimeConflictException("Cannot signup, User already exists with email "+signupDto.getEmail());
        User mappedUser = modelMapper.map(signupDto, User.class);

        mappedUser.setRoles(Set.of(Role.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser = userRepository.save(mappedUser);

        riderService.createNewRider(savedUser);
//        create user related entities

        walletService.createNewWallet(savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId, OnboardDriverDto onboardDriverDto) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User not found with id :"+ userId));

        if(user.getRoles().contains(Role.DRIVER)) {
            throw new RuntimeConflictException("User with id "+userId+" is already a Driver");
        }

        Driver createdDriver = Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(onboardDriverDto.getVehicleId())
                .available(true)
                .build();
        user.getRoles().add(Role.DRIVER);
        userRepository.save(user);
        return modelMapper.map(driverService.createNewDriver(createdDriver), DriverDto.class);
    }

    @Override
    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found " +
                "with id: "+userId));

        return jwtService.generateAccessToken(user);
    }
}
