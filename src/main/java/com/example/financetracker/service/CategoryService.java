package com.example.financetracker.service;

import com.example.financetracker.model.Category;
import com.example.financetracker.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

//    public Category updateCategory(Category category, Long id) {
//        Category categoryById = getCategoryById(id);
//        Category buildedCategory = Category.builder()
//                .id(categoryById.getId())
//                .name(category.getName())
//                .categoryBalance(category.getCategoryBalance())
//                .categoryLimit(category.getCategoryLimit())
//                .categoryLimitMark(category.getCategoryLimitMark())
//                .build();
//        return categoryRepository.save(buildedCategory);
//    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
