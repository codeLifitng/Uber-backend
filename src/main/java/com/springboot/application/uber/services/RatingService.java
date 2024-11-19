package com.springboot.application.uber.services;

import com.springboot.application.uber.dto.DriverDto;
import com.springboot.application.uber.dto.RiderDto;
import com.springboot.application.uber.entities.Ride;

public interface RatingService {
    DriverDto rateDriver(Ride ride, Integer rating);
    RiderDto rateRider(Ride ride, Integer rating);
    void createNewRating(Ride ride);
}
