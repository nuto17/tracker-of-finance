package com.example.financeTracker.Dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class WalletDto {

    private Long id;

    private String name;

    private BigDecimal balance;
}
