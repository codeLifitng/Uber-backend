package com.springboot.application.uber.strategies;

import com.springboot.application.uber.entities.Driver;
import com.springboot.application.uber.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
