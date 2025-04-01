package com.example.financeTracker.service;

import com.example.financeTracker.marks.Enum;
import com.example.financeTracker.model.Category;
import com.example.financeTracker.model.Expense;
import com.example.financeTracker.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("category with required id doesn't exist"));
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category, Long id) {
        Category categoryById = getCategoryById(id);
        Category buildedCategory = Category.builder()
                .id(categoryById.getId())
                .name(category.getName())
                .categoryBalance(category.getCategoryBalance())
                .categoryLimit(category.getCategoryLimit())
                .categoryLimitMark(category.getCategoryLimitMark())
                .build();
        return categoryRepository.save(buildedCategory);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    public void statusOfCategoryBalance(BigDecimal categoryBalanceWithExpense, BigDecimal categoryLimit, Category category){
        BigDecimal subtractBetweenLimitAndBalance = categoryLimit.subtract(categoryBalanceWithExpense);
        if(subtractBetweenLimitAndBalance.compareTo(BigDecimal.valueOf(1000))>0 || categoryLimit.compareTo(BigDecimal.ZERO)==0){
            category.setCategoryLimitMark(Enum.GOOD);
        }
        else if (subtractBetweenLimitAndBalance.compareTo(BigDecimal.valueOf(500))<=0){
            category.setCategoryLimitMark(Enum.NEAR);
        }
        else {category.setCategoryLimitMark(Enum.OVER);}
        category.setCategoryBalance(categoryBalanceWithExpense);
    }

    public void makeExpense(Expense expense){
        Category fullCategoryFromExpense = getCategoryById(expense.getCategory().getId());
        BigDecimal categoryLimit = fullCategoryFromExpense.getCategoryLimit();
        BigDecimal categoryBalanceWithExpense = fullCategoryFromExpense.getCategoryBalance().add(expense.getAmount());
        statusOfCategoryBalance(categoryBalanceWithExpense, categoryLimit, fullCategoryFromExpense);
        expense.setCategory(fullCategoryFromExpense);
        }

    public Category updateCategoryLimit(Long categoryId, BigDecimal limit){
        Category categoryById = getCategoryById(categoryId);
        categoryById.setCategoryLimit(limit);
        return updateCategory(categoryById, categoryId);
    }
}
