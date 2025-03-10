package com.example.financeTracker.mapper;

import com.example.financeTracker.Dto.CategoryDto;
import com.example.financeTracker.Dto.ExpenseDto;
import com.example.financeTracker.model.Category;
import com.example.financeTracker.model.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    @Mapping(target = "id", source = "expenseDto.id")
    @Mapping(target = "amount", source = "expenseDto.amount")
    @Mapping(target = "timeAdded", source = "expenseDto.timeAdded")
    @Mapping(target = "category", source = "expenseDto.categoryDto", qualifiedByName = "mapCategoryDtoToCategory")
    Expense toModel(ExpenseDto expenseDto);

    @Mapping(target = "id", source = "expense.id")
    @Mapping(target = "amount", source = "expense.amount")
    @Mapping(target = "timeAdded", source = "expense.timeAdded")
    @Mapping(target = "categoryDto", source = "expense.category", qualifiedByName = "mapCategoryToCategoryDto")
    ExpenseDto toDto(Expense expense);

    List<Expense> toModel(List<ExpenseDto> expensesDto);

    List<ExpenseDto> toDto(List<Expense> expenses);

    @Named("mapCategoryDtoToCategory")
    default Category mapCategoryDtoToCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        return category;
    }

    @Named("mapCategoryToCategoryDto")
    default CategoryDto mapCategoryToCategoryDto(Category category) {
        CategoryDto categoryDto = CategoryDto
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
        return categoryDto;
    }
}
