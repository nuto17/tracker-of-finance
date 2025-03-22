package com.example.financeTracker.dto;

import com.example.financeTracker.model.Category;
import com.example.financeTracker.model.Wallet;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ExpenseDto {

    private Long id;

    private LocalDateTime timeAdded;

    private BigDecimal amount;

    private CategoryDto category;

    private WalletDto wallet;
}
