package com.springboot.application.uber.services;

import com.springboot.application.uber.entities.Ride;
import com.springboot.application.uber.entities.User;
import com.springboot.application.uber.entities.Wallet;
import com.springboot.application.uber.entities.enums.TransactionMethod;

public interface WalletService {

    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);

}
