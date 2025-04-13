package com.example.financetracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class WalletDto {

    private Long id;

    private String name;

    private BigDecimal balance;

    @Schema(hidden = true)
    private LocalDateTime updateTime;
}
