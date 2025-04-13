package com.example.financeTracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "expenses")
@EntityListeners(AuditingEntityListener.class)
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "time_added", nullable = false)
    @CreatedDate
    @UpdateTimestamp
    private LocalDateTime timeAdded;

    @JoinColumn(name = "category_id", nullable = false)
    @ManyToOne
    private Category category;

    @JoinColumn(name = "wallet_id", nullable = false)
    @ManyToOne
    private Wallet wallet;
}
