package com.springboot.application.uber.services;

import com.springboot.application.uber.entities.Driver;
import com.springboot.application.uber.entities.Ride;
import com.springboot.application.uber.entities.RideRequest;
import com.springboot.application.uber.entities.Rider;
import com.springboot.application.uber.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
}
