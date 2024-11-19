package com.springboot.application.uber.repositories;

import com.springboot.application.uber.entities.Driver;
import com.springboot.application.uber.entities.Rating;
import com.springboot.application.uber.entities.Ride;
import com.springboot.application.uber.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    
    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);

    Optional<Rating> findByRide(Ride ride);
}