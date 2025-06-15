package io.github.nuto17.financetracker.dto;

import io.github.nuto17.financetracker.marks.TransactionType;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class TransactionDto {

    @Hidden
    private Long id;

    private LocalDateTime timeAdded;

    @Hidden
    private TransactionType type;

    private Long categoryId;

    private BigDecimal amount;
}
