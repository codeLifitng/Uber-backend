package com.springboot.application.uber.dto;

import com.springboot.application.uber.entities.User;
import lombok.Data;

import java.util.List;

@Data
public class WalletDto {

    private Long id;

    private User user;

    private Double balance;

    private List<WalletTransactionDto> transactions;
}
