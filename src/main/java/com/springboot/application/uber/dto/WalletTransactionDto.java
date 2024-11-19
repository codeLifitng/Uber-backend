package com.springboot.application.uber.dto;

import com.springboot.application.uber.entities.Ride;
import com.springboot.application.uber.entities.enums.TransactionMethod;
import com.springboot.application.uber.entities.enums.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WalletTransactionDto {

    private Long id;

    private Double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private Ride ride;

    private String transactionId;

    private WalletDto wallet;

    private LocalDateTime timeStamp;
}
