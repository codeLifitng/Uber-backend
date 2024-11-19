package com.springboot.application.uber.services;

import com.springboot.application.uber.entities.WalletTransaction;

public interface WalletTransactionService {

    void createNewWalletTransaction(WalletTransaction walletTransaction);
}
