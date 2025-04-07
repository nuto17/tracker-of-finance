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

    public void makeExpense(Expense expense) {
        Wallet fullWalletFromExpense = getWalletById(expense.getWallet().getId());
        if (fullWalletFromExpense.getBalance().compareTo(expense.getAmount()) >= 0) {
            BigDecimal subtractBetweenBalanceAndAmount = fullWalletFromExpense.getBalance().subtract(expense.getAmount());
            fullWalletFromExpense.setBalance(subtractBetweenBalanceAndAmount);
            Wallet updatedWallet = updateWallet(fullWalletFromExpense, fullWalletFromExpense.getId());
            expense.setWallet(updatedWallet);
        } else throw new IllegalArgumentException("balance cannot be less than expense");
    }

    public Wallet addMoneyToWalletById(BigDecimal sum, Long walletId) {
        Wallet walletById = getWalletById(walletId);
        walletById.setBalance(walletById.getBalance().add(sum));
        return updateWallet(walletById, walletId);
    }

    public void substractMoneyFromWalletById(BigDecimal sum, Long walletId) {
        Wallet walletById = getWalletById(walletId);
        walletById.setBalance(walletById.getBalance().subtract(sum));
        updateWallet(walletById, walletId);
    }
}
//в старый возращает но в новый не накидывает
//    public Wallet updateWalletFromExpense(Long oldWalletId, Long newWalletId, BigDecimal oldAmount, BigDecimal newAmount) {
//        //меняется только сумма
//        if (oldWalletId.equals(newWalletId)) {
//            addMoneyToWalletById(oldAmount, oldWalletId);
//            substractMoneyFromWalletById(newAmount, oldWalletId);
//            return getWalletById(oldWalletId);
//        } else if (newAmount.compareTo(oldAmount) == 0) {
//            addMoneyToWalletById(oldAmount, oldWalletId);
//            substractMoneyFromWalletById(oldAmount, newWalletId);
//            return getWalletById(newWalletId);
//        } else {
//            addMoneyToWalletById(oldAmount, oldWalletId);
//            substractMoneyFromWalletById(newAmount, newWalletId);
//            return getWalletById(newWalletId);
//        }
//    }
