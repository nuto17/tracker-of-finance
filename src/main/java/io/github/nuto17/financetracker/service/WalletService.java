package io.github.nuto17.financetracker.service;

import io.github.nuto17.financetracker.exception.InsufficientBalanceException;
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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public List<Wallet> getWallets() {
        return walletRepository.findAll();
    }

    public Wallet getWalletById(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("wallet with id=" + id + " doesn't exist"));
    }

    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public void deleteWalletById(Long id) {
        walletRepository.deleteById(id);
    }

    public void validateSufficientBalance(Wallet wallet, BigDecimal amount) {
        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
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
        categoryService.getCategoryById(transaction.getCategoryId());
        switch (transaction.getType()) {
            case EXPENSE -> expenseFromWallet(transaction.getAmount(), walletFromTransaction);
            case DEPOSIT -> {
                depositWallet(transaction.getAmount(), walletFromTransaction);
                transaction.setCategoryId(null);
            }
        }
        transactionRepository.save(transaction);
        return walletFromTransaction;
    }

    public List<LocalDateTime> validationDatePeriod(LocalDate startDate, LocalDate finishDate) {
        List<LocalDateTime> period = new ArrayList<>();
        if (startDate == null || finishDate == null) {
            throw new IllegalArgumentException("Date can't be null");
        } else if (finishDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Finish date can't be early than start date");
        }
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime finishDateTime = finishDate.atTime(LocalTime.MAX);

        if (startDate.isEqual(finishDate)) {
            finishDateTime = startDate.plusDays(1).atTime(LocalTime.MAX);
        }
        return List.of(startDateTime, finishDateTime);
    }


    public List<Transaction> getAllTransactionsByWalletIdAndType(Long walletId, TransactionType type) {
        if (type != null) {
            return transactionRepository.findAllByWalletIdAndType(walletId, type);
        }
        return transactionRepository.findAllByWalletId(walletId);
    }

    public List<Transaction> getTransactionsByPeriodAndType(Long walletId, TransactionType type, LocalDate startDate, LocalDate finishDate) {
        getWalletById(walletId);
        List<LocalDateTime> period = validationDatePeriod(startDate, finishDate);
        if (type != null) {
            return transactionRepository.findAllByWalletIdAndTypeAndTimeAddedBetween(walletId, type, period.get(0), period.get(1));
        }
        return transactionRepository.findAllByWalletIdAndTimeAddedBetween(walletId, period.get(0), period.get(1));
    }

    public BigDecimal getSumTransactionsByCategoryId(Long walletId, Long categoryId) {
        getWalletById(walletId);
        categoryService.getCategoryById(categoryId);
        return transactionRepository.findAllByWalletIdAndCategoryId(walletId, categoryId).stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Transaction> getAllTransactionsByCategoryId(Long walletId, Long categoryId) {
        categoryService.getCategoryById(categoryId);
        return transactionRepository.findAllByWalletIdAndCategoryId(walletId, categoryId);
    }
}