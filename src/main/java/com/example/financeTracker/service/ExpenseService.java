package com.example.financeTracker.service;

import com.example.financeTracker.model.Expense;
import com.example.financeTracker.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final WalletService walletService;

    public List<Expense> getExpenses() {
        List<Expense> allExpenses = expenseRepository.findAll();
        return allExpenses;
    }

    public Expense getExpenseById(Long id) {
        Expense expenseById = expenseRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense with required id doesn't exist"));
        return expenseById;
    }

    public Expense createExpense(Expense expense) {
        Boolean expenseOperation = walletService.makeExpense(expense);
        if (expenseOperation){
            Expense createdExpense = expenseRepository.save(expense);
            return createdExpense;
        }
        else throw new IllegalArgumentException("balance cannot be less than expense");
    }

    public Expense updateExpense(Expense expense, Long id) {
        Expense expenseById = getExpenseById(id);
        Expense buildedExpense = Expense
                .builder()
                .id(expenseById.getId())
                .amount(expense.getAmount())
                .timeAdded(expense.getTimeAdded())
                .category(expense.getCategory())
                .walletId(expense.getWalletId())
                .build();
        Expense savedBuildedExpense = expenseRepository.save(buildedExpense);
        return savedBuildedExpense;
    }

    public void deleteExpenseById(Long id) {
        Expense expenseById = getExpenseById(id);
        expenseRepository.delete(expenseById);
    }
}
