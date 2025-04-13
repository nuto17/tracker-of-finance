package com.example.financeTracker.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SubscriptionDto {

    private Long id;

    private String name;

    private LocalDate dateToPay;

    private BigDecimal price;
}
