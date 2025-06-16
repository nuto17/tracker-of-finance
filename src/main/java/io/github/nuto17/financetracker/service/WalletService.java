package io.github.nuto17.financetracker.service;

import io.github.nuto17.financetracker.exception.BalanceCanNotBeLessAmount;
import io.github.nuto17.financetracker.marks.TransactionType;
import io.github.nuto17.financetracker.model.Transaction;
import io.github.nuto17.financetracker.model.Wallet;
import io.github.nuto17.financetracker.repository.CategoryRepository;
import io.github.nuto17.financetracker.repository.TransactionRepository;
import io.github.nuto17.financetracker.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

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

    public void validateWalletExists(Long walletId) {
        if (!walletRepository.existsById(walletId)) {
            throw new EntityNotFoundException("wallet with required id=" + walletId + " doesn't exist");
        }
    }

    public void validateCategoryExist(Long categoryId) {
        if (!categoryRepository.existsCategoryById(categoryId)) {
            throw new EntityNotFoundException("category with required id=" + categoryId + " doesn't exist");
        }
    }

    public void validateSufficientBalance(Wallet wallet, BigDecimal amount) {
        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new BalanceCanNotBeLessAmount();
        }
    }

    public void expenseFromWallet(BigDecimal transactionAmount, Wallet walletFromTransaction) {
        validateSufficientBalance(walletFromTransaction, transactionAmount);
        walletFromTransaction.setBalance(walletFromTransaction.getBalance().subtract(transactionAmount));
    }

    public void depositWallet(BigDecimal transactionAmount, Wallet walletFromTransaction) {
        walletFromTransaction.setBalance(walletFromTransaction.getBalance().add(transactionAmount));
    }

    @Transactional
    public Wallet operationWallet(Transaction transaction) {
        Wallet walletFromTransaction = getWalletById(transaction.getWalletId());
        switch (transaction.getType()) {
            case EXPENSE -> expenseFromWallet(transaction.getAmount(), walletFromTransaction);
            case DEPOSIT -> depositWallet(transaction.getAmount(), walletFromTransaction);
        }
        transactionRepository.save(transaction);
        return walletFromTransaction;
    }

    public LocalDateTime convertDateToDateTime(LocalDate date) {
        return date.atStartOfDay();
    }

    public LocalDateTime[] validationDatePeriod(LocalDate firstDate, LocalDate secondDate) {
        if (firstDate == null || secondDate == null) {
            throw new IllegalArgumentException("Date can't be null");
        } else if (secondDate.isBefore(firstDate)) {
            throw new IllegalArgumentException("Second date can't be early than first date");
        }
        return new LocalDateTime[]{convertDateToDateTime(firstDate), convertDateToDateTime(secondDate).plusDays(1)};
    }


    public List<Transaction> getAllTransactionsByWalletIdAndType(Long walletId, TransactionType type) {
        if (type!=null){
            return transactionRepository.findAllByWalletIdAndType(walletId,type);
        }
        return transactionRepository.findAllByWalletId(walletId);
    }

    public List<Transaction> getTransactionsByPeriodAndType(Long walletId, TransactionType type, LocalDate firstDate, LocalDate secondDate){
        validateWalletExists(walletId);
        LocalDateTime[] dates = validationDatePeriod(firstDate, secondDate);
        if (type!=null){
            return transactionRepository.findAllByWalletIdAndTypeAndTimeAddedBetween(walletId,type,dates[0],dates[1]);
        }
        return transactionRepository.findAllByWalletIdAndTimeAddedBetween(walletId,dates[0],dates[1]);
    }

    public BigDecimal getSumTransactionsByCategoryId(Long walletId, Long categoryId) {
        validateWalletExists(walletId);
        validateCategoryExist(categoryId);
        return transactionRepository.findAllByWalletIdAndCategoryId(walletId, categoryId).stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}