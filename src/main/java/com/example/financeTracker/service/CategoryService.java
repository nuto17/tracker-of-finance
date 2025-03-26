package com.example.financeTracker.service;

import com.example.financeTracker.marks.MarksWithLimit;
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

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category statusOfCategoryBalance(BigDecimal categoryBalanceWithExpense, BigDecimal categoryLimit, Category category){
        BigDecimal subtractBetweenLimitAndBalance = categoryLimit.subtract(categoryBalanceWithExpense);
        if(subtractBetweenLimitAndBalance.compareTo(BigDecimal.valueOf(1000))>0 || categoryLimit.compareTo(BigDecimal.ZERO)==0){
            category.setCategoryLimitMark(MarksWithLimit.GOOD);
        }
        else if (subtractBetweenLimitAndBalance.compareTo(BigDecimal.valueOf(500))>0){
            category.setCategoryLimitMark(MarksWithLimit.NEAR);
        }
        else {category.setCategoryLimitMark(MarksWithLimit.OVER);}
        category.setCategoryBalance(categoryBalanceWithExpense);
        return category;
    }

    public boolean makeExpense(Expense expense){
        Long categoryId = expense.getCategory().getId();
        Category categoryById = getCategoryById(categoryId);
        BigDecimal categoryLimit = categoryById.getCategoryLimit();
        BigDecimal categoryBalanceWithExpense = categoryById.getCategoryBalance().add(expense.getAmount());
        MarksWithLimit categoryMark = statusOfCategoryBalance(categoryBalanceWithExpense, categoryLimit, categoryById).getCategoryLimitMark();
        if(categoryMark.equals(MarksWithLimit.OVER)){
            return false;
        }
        return true;
        }

    public Category updateCategoryLimit(Long categoryId, Category category){
        Category categoryById = getCategoryById(categoryId);
        BigDecimal newCategoryLimit = category.getCategoryLimit();
        categoryById.setCategoryLimit(newCategoryLimit);
        return updateCategory(categoryById, categoryById.getId());
    }
}
