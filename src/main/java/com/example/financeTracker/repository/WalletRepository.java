package com.example.financeTracker.repository;

import com.example.financeTracker.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository <Wallet, Long> {}
