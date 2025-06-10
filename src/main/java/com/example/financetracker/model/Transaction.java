package com.example.financetracker.model;

import com.example.financetracker.marks.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "time_added", nullable = false)
    @CreatedDate
    private LocalDateTime timeAdded;

    @Column(name = "start_date", nullable = true)
    private LocalDate startDate;

    @Column(name = "last_date", nullable = true)
    private LocalDate lastDate;

    @Column(name = "category_id", nullable = true)
    private Long categoryId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "wallet_id", nullable = false)
    private Long walletId;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type", nullable = false)
    private TransactionType type;
}
