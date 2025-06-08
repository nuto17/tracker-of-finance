package com.example.financetracker.model;

import com.example.financetracker.marks.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
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

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "time_added")
    @UpdateTimestamp
    @CreatedDate
    private LocalDateTime timeAdded;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "last_date")
    private LocalDate lastDate;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "amount")
    private BigDecimal amountTransaction;

    @Column(name = "wallet_id")
    private Long walletId;

    @Enumerated
    @Column(name = "operation_type")
    private TransactionType type;
}
