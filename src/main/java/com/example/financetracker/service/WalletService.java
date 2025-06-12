package com.example.financetracker.service;

import com.example.financetracker.exception.BalanceCanNotBeLessAmount;
import com.example.financetracker.marks.TransactionType;
import com.example.financetracker.model.Transaction;
import com.example.financetracker.model.Wallet;
import com.example.financetracker.repository.TransactionRepository;
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
    private final TransactionRepository transactionRepository;

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

    public void validateSufficientBalance(Wallet wallet, BigDecimal amount) {
        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new BalanceCanNotBeLessAmount();
        }
    }

    public void expenseFromWallet(Transaction transaction) {
        Wallet walletById = getWalletById(transaction.getWalletId());
        validateSufficientBalance(walletById, transaction.getAmount());
        walletById.setBalance(walletById.getBalance().subtract(transaction.getAmount()));
        transactionRepository.save(transaction);
    }

    public void depositWallet(Transaction transaction) {
        Wallet walletById = getWalletById(transaction.getWalletId());
        walletById.setBalance(walletById.getBalance().add(transaction.getAmount()));
        transactionRepository.save(transaction);
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

    public List<Transaction> getAllTransactionsByWalletIdAndType(Long walletId, TransactionType type) {
        if(type!=null){
           return transactionRepository.findAllByWalletIdAndType(walletId,type);
        }
        else
            return transactionRepository.findAllByWalletId(walletId);
    }
}