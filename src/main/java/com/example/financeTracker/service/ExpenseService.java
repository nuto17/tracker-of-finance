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
    private final CategoryService categoryService;

    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense with required id doesn't exist"));
    }

    public Expense createExpense(Expense expense) {
        walletService.makeExpense(expense);
        categoryService.makeExpense(expense);
        return expenseRepository.save(expense);
    }

//    public Expense updateExpense(Expense expense, Long id) {
//        Long oldWalletId = getExpenseById(id).getWallet().getId();
//        BigDecimal oldAmount = getExpenseById(id).getAmount();
//        BigDecimal newAmount = expense.getAmount();
//        Category oldCategory = getExpenseById(id).getCategory();
//        Long newCategoryId = expense.getCategory().getId();
//
//        Wallet updatedWallet = walletService.updateWalletFromExpense(oldWalletId, expense.getWallet().getId(), oldAmount, newAmount);
//        return Expense.builder()
//                .id(id)
//                .category(expense.getCategory())
//                .timeAdded(expense.getTimeAdded())
//                .wallet(updatedWallet)
//                .build();
//    }
    //дописать логику( нужно чтобы при обновлении траты -> шел перерасчет всего: кошелек, баланс категории

    // пока не воркает


//        if(newExpense.getWallet().getId().compareTo(walletService.getWalletById(oldWalletId).getId())!=0 && isNotAmountChanged){
//            walletService.addMoneyToWalletById(oldAmount,oldWalletId);
//            walletService.substractMoneyFromWalletById(oldAmount,newExpense.getWallet().getId());
//        }

    public void deleteExpenseById(Long id) {
        expenseRepository.deleteById(id);
    }
}
