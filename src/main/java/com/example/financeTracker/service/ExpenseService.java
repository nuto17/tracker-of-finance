package com.example.financeTracker.service;

import com.example.financeTracker.model.Category;
import com.example.financeTracker.model.Expense;
import com.example.financeTracker.model.Wallet;
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
    private final CategoryService categoryService;

    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense with required id doesn't exist"));
    }

    public Expense createExpense(Expense expense) {
        Boolean isExpenseOperationMaked = walletService.makeExpense(expense);
        Boolean isExpenseOperationWithLimitMaked = categoryService.makeExpense(expense);
        if (isExpenseOperationMaked){
            //logic with category
            Long categoryIdFromExpense = expense.getCategory().getId();
            Category categoryById = categoryService.getCategoryById(categoryIdFromExpense);
            Category updatedCategory = categoryService.updateCategory(categoryById, categoryIdFromExpense);
            expense.setCategory(updatedCategory);

            //logic with wallet
            Long walletIdFromExpense = expense.getWallet().getId();
            Wallet walletByExpenseId = walletService.getWalletById(walletIdFromExpense);
            expense.setWallet(walletByExpenseId);

            return expenseRepository.save(expense);
        }
        throw new IllegalArgumentException("balance cannot be less than expense");
    }

    public Expense updateExpense(Expense expense, Long id) {
        Expense expenseById = getExpenseById(id);
        Expense buildedExpense = Expense
                .builder()
                .id(expenseById.getId())
                .amount(expense.getAmount())
                .timeAdded(expense.getTimeAdded())
                .category(expense.getCategory())
                .wallet(expense.getWallet())
                .build();
        return expenseRepository.save(buildedExpense);
    }

    public void deleteExpenseById(Long id) {
        Expense expenseById = getExpenseById(id);
        expenseRepository.delete(expenseById);
    }
}
