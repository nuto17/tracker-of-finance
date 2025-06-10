package com.example.financetracker.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class TransactionDto {

    private Long id;

    private LocalDateTime timeAdded;

    private Long categoryId;

    private BigDecimal amount;
}
