package com.example.financetracker.dto;

import com.example.financetracker.marks.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class TransactionDto {

    private Long id;

    private LocalDateTime timeAdded;

    private LocalDate startDate;

    private LocalDate lastDate;

    private Long categoryId;

    private BigDecimal amount;

    private Long walletId;

    private TransactionType type;
}
