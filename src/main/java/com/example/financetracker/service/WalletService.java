package com.example.financetracker.service;

import com.example.financetracker.model.Transaction;
import com.example.financetracker.model.Wallet;
import com.example.financetracker.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionService transactionService;

    public List<Wallet> getWallets() {
        return walletRepository.findAll();
    }

    public Wallet getWalletById(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("wallet with id doesn't exist"));
    }

    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public Wallet updateWallet(Wallet wallet, Long id) {
        Wallet walletById = getWalletById(id);
        Wallet builedWallet = Wallet.builder()
                .id(walletById.getId())
                .name(wallet.getName())
                .balance(wallet.getBalance())
                .updateTime(wallet.getUpdateTime())
                .build();
        return walletRepository.save(builedWallet);
    }

    public void deleteWalletById(Long id) {
        walletRepository.deleteById(id);
    }

    public boolean isAmountLessWalletBalance(Wallet wallet, BigDecimal amount) {
        return wallet.getBalance().compareTo(amount) >= 0;
    }

    public void expenseFromWallet(Transaction transaction) {
        Wallet walletById = getWalletById(transaction.getWalletId());
        if (isAmountLessWalletBalance(walletById, transaction.getAmount())) {
            walletById.setBalance(walletById.getBalance().subtract(transaction.getAmount()));
        }
        transactionService.saveTransaction(transaction);
    }

    public void depositWallet(Transaction transaction) {
        Wallet walletById = getWalletById(transaction.getWalletId());
        walletById.setBalance(walletById.getBalance().add(transaction.getAmount()));
        transactionService.saveTransaction(transaction);
    }

    @Transactional
    public Wallet operationWallet(Transaction transaction) {
        Wallet walletById = getWalletById(transaction.getWalletId());
        switch (transaction.getType()) {
            case EXPENSE -> expenseFromWallet(transaction);
            case DEPOSIT -> depositWallet(transaction);
        }
        return getWalletById(transaction.getWalletId());
    }
}