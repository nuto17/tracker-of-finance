package com.example.financetracker.service;

import com.example.financetracker.model.Expense;
import com.example.financetracker.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense with required id doesn't exist"));
    }

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
}
