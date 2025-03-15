package com.example.financeTracker.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExpenseDto {

    private Long id;

    private CategoryDto category;

    private Date timeAdded;

    private BigDecimal amount;

    private Long walletId;
}
