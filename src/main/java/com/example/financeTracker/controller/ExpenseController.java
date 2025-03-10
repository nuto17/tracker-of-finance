package com.example.financeTracker.controller;

import com.example.financeTracker.Dto.ExpenseDto;
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
    public List<ExpenseDto> getExpenses() {
        List<Expense> expenses = expenseService.getExpenses();
        List<ExpenseDto> expensesDto = mapper.toDto(expenses);
        return expensesDto;
    }

    @GetMapping("/{id}")
    public ExpenseDto getExpenseById(@PathVariable("id") Long id) {
        Expense expenseById = expenseService.getExpenseById(id);
        ExpenseDto expenseByIdDto = mapper.toDto(expenseById);
        return expenseByIdDto;
    }

    @PostMapping
    public ExpenseDto createExpense(@RequestBody ExpenseDto expenseDto) {
        Expense expense = mapper.toModel(expenseDto);
        Expense createdExpense = expenseService.createExpense(expense);
        ExpenseDto createdExpenseDto = mapper.toDto(createdExpense);
        return createdExpenseDto;
    }

    @PutMapping("/{id}")
    public ExpenseDto updateExpense(@PathVariable("id") Long id, @RequestBody ExpenseDto expenseDto) {
        Expense expense = mapper.toModel(expenseDto);
        Expense updatedExpense = expenseService.updateExpense(expense, id);
        ExpenseDto updatedExpenseDto = mapper.toDto(updatedExpense);
        return updatedExpenseDto;
    }

    @DeleteMapping("/{id}")
    public void deleteExpenseByID(@PathVariable("id") Long id) {
        expenseService.deleteExpenseById(id);
    }
}
