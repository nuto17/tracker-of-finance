package com.example.financetracker.service;

import com.example.financetracker.model.Transaction;
import com.example.financetracker.model.Wallet;
import com.example.financetracker.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TransactionService {

    private final WalletService walletService;
    private final TransactionRepository transactionRepository;

    @Transactional
    public Transaction depositToWallet(Transaction transaction) {
        Wallet walletFromTransaction = walletService.getWalletById(transaction.getWalletId());
        walletFromTransaction.setBalance(walletFromTransaction.getBalance().add(transaction.getAmount()));
        return transactionRepository.save(transaction);
    }
}
