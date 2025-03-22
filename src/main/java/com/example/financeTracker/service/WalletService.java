package com.example.financeTracker.service;

import com.example.financeTracker.model.Expense;
import com.example.financeTracker.model.Wallet;
import com.example.financeTracker.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public List<Wallet> getWallets() {
        List<Wallet> wallets = walletRepository.findAll();
        return wallets;
    }

    public Wallet getWalletById(Long id) {
        Wallet walletById = walletRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("wallet with id doesn't exist"));
        return walletById;
    }

    public Wallet createWallet(Wallet wallet) {
        Wallet createdWallet = walletRepository.save(wallet);
        return createdWallet;
    }

    public Wallet updateWallet(Wallet wallet, Long id) {
        Wallet walletById = getWalletById(id);
        Wallet builedWallet = Wallet.builder()
                .id(walletById.getId())
                .name(wallet.getName())
                .balance(wallet.getBalance())
                .build();
        Wallet savedBuilded = walletRepository.save(builedWallet);
        return savedBuilded;
    }

    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }

    public Boolean makeExpense(Expense expense) {
        Wallet walletById = getWalletById(expense.getWallet().getId());
        if (walletById.getBalance().compareTo(expense.getAmount()) >= 0) {
            BigDecimal subtractBetweenBalanceAndAmount = walletById.getBalance().subtract(expense.getAmount());
            walletById.setBalance(subtractBetweenBalanceAndAmount);
            Wallet updatedWallet = updateWallet(walletById, walletById.getId());
            return true;
        }
        return false;
    }
}
