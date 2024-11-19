package com.springboot.application.uber.strategies.impl;

import com.springboot.application.uber.entities.Driver;
import com.springboot.application.uber.entities.RideRequest;
import com.springboot.application.uber.repositories.DriverRepository;
import com.springboot.application.uber.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
    }
}
