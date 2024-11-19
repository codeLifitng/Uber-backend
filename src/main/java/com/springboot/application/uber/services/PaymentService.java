package com.springboot.application.uber.services;

import com.springboot.application.uber.entities.Payment;
import com.springboot.application.uber.entities.Ride;
import com.springboot.application.uber.entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
