package com.springboot.application.uber.controllers;

import com.springboot.application.uber.dto.DriverDto;
import com.springboot.application.uber.dto.RideDto;
import com.springboot.application.uber.dto.RideStartDto;
import com.springboot.application.uber.dto.RiderDto;
import com.springboot.application.uber.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId) {
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideRequestId}")
    public ResponseEntity<RideDto> startRide(@PathVariable Long rideRequestId,
                                             @RequestBody RideStartDto rideStartDto) {
        return ResponseEntity.ok(driverService.startRide(rideRequestId, rideStartDto.getOtp()));
    }

    @PostMapping("/endRide/{rideId}")
    public ResponseEntity<RideDto> startRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(driverService.endRide(rideId));
    }

    @PostMapping(path = "/cancelRide/{rideId}")
    public ResponseEntity<RideDto> cancelride(@PathVariable Long rideId) {
        return ResponseEntity.ok(driverService.cancelRide(rideId));
    }

    @GetMapping(path = "/getMyProfile")
    public ResponseEntity<DriverDto> getMyProfile() {
        return ResponseEntity.ok(driverService.getMyProfile());
    }

    @GetMapping(path = "/getMyRides")
    public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                       @RequestParam(defaultValue = "10", required = false) Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageOffset, pageSize,
                Sort.by(Sort.Direction.DESC, "createdTime", "id"));
        return ResponseEntity.ok(driverService.getAllMyRides(pageRequest));
    }

    @PostMapping(path = "/rateRider/{rideId}/{rating}")
    public ResponseEntity<RiderDto> rateRider(@PathVariable Long rideId,
                                              @PathVariable Integer rating) {
        return ResponseEntity.ok(driverService.rateRider(rideId, rating));
    }

}
