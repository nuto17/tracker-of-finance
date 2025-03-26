package com.example.financeTracker.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ExpenseDtoForOutput {

    private Long id;

    private LocalDateTime timeAdded;

    private BigDecimal amount;

    private CategoryDto category;

    private WalletDto wallet;

}
