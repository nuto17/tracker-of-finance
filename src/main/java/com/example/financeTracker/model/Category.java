package com.example.financeTracker.model;

import com.example.financeTracker.marks.Enum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private BigDecimal categoryBalance;

    @Column(name = "category_limit")
    private BigDecimal categoryLimit;

    @Enumerated(EnumType.STRING)
    @Column(name = "limit_status")
    private Enum categoryLimitMark;
}
