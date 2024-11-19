package com.springboot.application.uber.strategies;

import com.springboot.application.uber.entities.Payment;

public interface PaymentStrategy {

    static final double PLATFORM_COMMISSION = 0.3;

    void processPayment(Payment payment);

}
