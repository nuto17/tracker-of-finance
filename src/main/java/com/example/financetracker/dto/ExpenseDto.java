package com.example.financetracker.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ExpenseDto {

    private Long id;

    private LocalDateTime timeAdded;

    private LocalDate dateAdded;

    private BigDecimal amount;

    private WalletDto wallet;
}
