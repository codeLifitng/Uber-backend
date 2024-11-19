package com.springboot.application.uber.services.impl;

import com.springboot.application.uber.entities.Payment;
import com.springboot.application.uber.entities.Ride;
import com.springboot.application.uber.entities.enums.PaymentStatus;
import com.springboot.application.uber.exceptions.ResourceNotFoundException;
import com.springboot.application.uber.repositories.PaymentRepository;
import com.springboot.application.uber.services.PaymentService;
import com.springboot.application.uber.strategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;

    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Payment not found for ride with id: "+ride.getId())
                        );
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment.builder()
                .ride(ride)
                .paymentMethod(ride.getPaymentMethod())
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .build();

        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);
    }
}
