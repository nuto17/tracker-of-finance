package com.example.financetracker.repository;

import com.example.financetracker.marks.TransactionType;
import com.example.financetracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByWalletIdAndType(Long walletId, TransactionType type);

    List<Transaction> findAllByWalletId(Long walletId);

    List<Transaction> findAllByWalletIdAndTypeAndTimeAddedBetween(Long walletId, TransactionType type, LocalDateTime startDate, LocalDateTime endDate);

    List<Transaction> findAllByWalletIdAndTimeAddedBetween(Long walletId,LocalDateTime startDate, LocalDateTime endDate);
}
