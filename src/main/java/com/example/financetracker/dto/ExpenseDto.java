package com.example.financetracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ExpenseDto {

    private Long id;

    @Schema(hidden = true)
    private LocalDateTime timeAdded;

    private LocalDate dateAdded;

    private BigDecimal amount;

    private Long walletId;

    private Long categoryId;

    @Schema(hidden = true)
    private CategoryDto categoryDto;

    @Schema(hidden = true)
    private WalletDto walletDto;
}
