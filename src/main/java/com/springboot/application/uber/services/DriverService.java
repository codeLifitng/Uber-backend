package com.springboot.application.uber.services;

import com.springboot.application.uber.dto.DriverDto;
import com.springboot.application.uber.dto.RideDto;
import com.springboot.application.uber.dto.RiderDto;
import com.springboot.application.uber.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {

    Driver createNewDriver(Driver driver);

    RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId, String otp);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId, Integer rating);

    DriverDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Driver getCurrentDriver();

    Driver updateDriverAvailability(Driver driver, boolean available);
}
