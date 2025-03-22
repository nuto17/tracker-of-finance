package com.example.financeTracker.service;

import com.example.financeTracker.model.Category;
import com.example.financeTracker.model.Expense;
import com.example.financeTracker.model.Wallet;
import com.example.financeTracker.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final WalletService walletService;
    private final CategoryService categoryService;

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
        Boolean isExpenseOperationMaked = walletService.makeExpense(expense);
        if (isExpenseOperationMaked){
            //logic with category
            Category category = expense.getCategory();
            Long categoryIdFromExpense = category.getId();
            Category categoryById = categoryService.getCategoryById(categoryIdFromExpense);
            BigDecimal categoryBalance = categoryById.getCategoryBalance();
            BigDecimal updatedCategoryBalance = categoryBalance.add(expense.getAmount());
            categoryById.setCategoryBalance(updatedCategoryBalance);
            Category updatedCategory = categoryService.updateCategory(categoryById, categoryIdFromExpense);
            expense.setCategory(updatedCategory);

            //logic with wallet
            Wallet wallet = expense.getWallet();
            Long walletIdFromExpense = wallet.getId();
            Wallet walletByExpenseId = walletService.getWalletById(walletIdFromExpense);
            expense.setWallet(walletByExpenseId);

            Expense createdExpense = expenseRepository.save(expense);
            return createdExpense;
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
        Expense savedBuildedExpense = expenseRepository.save(buildedExpense);
        return savedBuildedExpense;
    }

    public void deleteExpenseById(Long id) {
        Expense expenseById = getExpenseById(id);
        expenseRepository.delete(expenseById);
    }
}
