package com.example.financeTracker.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class SubscriptionDto {

    private Long id;

    private String name;

    private Date dateToPay;

    private BigDecimal price;
}
