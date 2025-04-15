package com.example.financetracker.controller;

import com.example.financetracker.dto.ExpenseDto;
import com.example.financetracker.mapper.ExpenseMapper;
import com.example.financetracker.model.Expense;
import com.example.financetracker.service.ExpenseService;
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
        return mapper.toDto(expenses);
    }

    @GetMapping("/{id}")
    public ExpenseDto getExpenseById(@PathVariable("id") Long id) {
        Expense expenseById = expenseService.getExpenseById(id);
        return mapper.toDto(expenseById);
    }

    @PostMapping
    public ExpenseDto createExpense(@RequestBody ExpenseDto expenseDto) {
        Expense expense = mapper.toModel(expenseDto);
        Expense createdExpense = expenseService.createExpense(expense);
        return mapper.toDto(createdExpense);
    }
}
