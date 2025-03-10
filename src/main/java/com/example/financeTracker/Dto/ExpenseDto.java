package com.example.financeTracker.Dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExpenseDto {

    private Long id;

    private CategoryDto categoryDto;

    private Date timeAdded;

    private BigDecimal amount;
}
