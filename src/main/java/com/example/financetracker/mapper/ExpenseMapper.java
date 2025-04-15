package com.example.financetracker.mapper;

import com.example.financetracker.dto.ExpenseDto;
import com.example.financetracker.model.Expense;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    Expense toModel(ExpenseDto expenseDto);
    ExpenseDto toDto(Expense expense);

    List<Expense> toModel(List<ExpenseDto> expensesDto);
    List<ExpenseDto> toDto(List<Expense> expenses);
}
