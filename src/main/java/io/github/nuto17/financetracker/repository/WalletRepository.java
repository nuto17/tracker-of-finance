package io.github.nuto17.financetracker.repository;

import io.github.nuto17.financetracker.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {}
