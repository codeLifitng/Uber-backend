package com.springboot.application.uber.strategies.impl;

import com.springboot.application.uber.entities.Driver;
import com.springboot.application.uber.entities.Payment;
import com.springboot.application.uber.entities.enums.PaymentStatus;
import com.springboot.application.uber.entities.enums.TransactionMethod;
import com.springboot.application.uber.repositories.PaymentRepository;
import com.springboot.application.uber.services.WalletService;
import com.springboot.application.uber.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//Rider -> 100
//Driver -> 70 Deduct 30Rs from Driver's wallet

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;

        walletService.deductMoneyFromWallet(driver.getUser(), platformCommission,
                null, payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
