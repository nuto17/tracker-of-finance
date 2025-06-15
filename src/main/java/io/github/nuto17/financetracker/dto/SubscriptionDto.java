package io.github.nuto17.financetracker.dto;

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
