package io.github.nuto17.financetracker.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CategoryDto {

    private Long id;

    private String name;

    private BigDecimal limit;

    private BigDecimal balance;
}
