package com.example.financeTracker.controller;

import com.example.financeTracker.dto.ExpenseDto;
import com.example.financeTracker.dto.ExpenseDtoForOutput;
import com.example.financeTracker.mapper.ExpenseMapper;
import com.example.financeTracker.model.Expense;
import com.example.financeTracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseMapper mapper;

    @GetMapping
    public List<ExpenseDtoForOutput> getExpenses() {
        List<Expense> expenses = expenseService.getExpenses();
        return mapper.toDto(expenses);
    }

    @GetMapping("/{id}")
    public ExpenseDtoForOutput getExpenseById(@PathVariable("id") Long id) {
        Expense expenseById = expenseService.getExpenseById(id);
        return mapper.toDto(expenseById);
    }

    @PostMapping
    public ExpenseDtoForOutput createExpense(@RequestBody ExpenseDto expenseDto) {
        Expense expense = mapper.toModel(expenseDto);
        Expense createdExpense = expenseService.createExpense(expense);
        return mapper.toDto(createdExpense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpenseByID(@PathVariable("id") Long id) {
        expenseService.deleteExpenseById(id);
    }
}
