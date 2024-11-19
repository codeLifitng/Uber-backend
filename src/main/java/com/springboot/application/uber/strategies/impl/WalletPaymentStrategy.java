package com.springboot.application.uber.strategies.impl;

import com.springboot.application.uber.entities.Driver;
import com.springboot.application.uber.entities.Payment;
import com.springboot.application.uber.entities.Rider;
import com.springboot.application.uber.entities.enums.PaymentStatus;
import com.springboot.application.uber.entities.enums.TransactionMethod;
import com.springboot.application.uber.repositories.PaymentRepository;
import com.springboot.application.uber.services.WalletService;
import com.springboot.application.uber.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(),
                null, payment.getRide(), TransactionMethod.RIDE);

        double driverCut = payment.getAmount() * (1 - PLATFORM_COMMISSION);

        walletService.addMoneyToWallet(driver.getUser(), driverCut,
                null, payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
