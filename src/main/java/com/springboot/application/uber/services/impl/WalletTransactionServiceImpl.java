package com.springboot.application.uber.services.impl;

import com.springboot.application.uber.entities.WalletTransaction;
import com.springboot.application.uber.repositories.WalletTransactionRepository;
import com.springboot.application.uber.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);
    }
}
