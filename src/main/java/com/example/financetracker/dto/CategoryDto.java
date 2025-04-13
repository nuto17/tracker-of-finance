package com.example.financetracker.dto;

import com.example.financetracker.marks.Enum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CategoryDto {

    private Long id;

    private String name;

    private BigDecimal limit;

    @Schema(hidden = true)
    private BigDecimal balance;

    @Schema(hidden = true)
    private Enum limitStatus;
}
