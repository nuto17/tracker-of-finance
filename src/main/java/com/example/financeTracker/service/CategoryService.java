package com.example.financeTracker.service;

import com.example.financeTracker.model.Category;
import com.example.financeTracker.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    public Category getCategoryById(Long id) {
        Category categoryById = categoryRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("category with required id doesn't exist"));
        return categoryById;
    }

    public Category createCategory(Category category) {
        Category createdCategory = categoryRepository.save(category);
        return createdCategory;
    }

    public Category updateCategory(Category category, Long id) {
        Category categoryById = getCategoryById(id);
        Category buildedCategory = Category.builder()
                .id(categoryById.getId())
                .name(category.getName())
                .build();
        Category savedBuildedCategory = categoryRepository.save(buildedCategory);
        return savedBuildedCategory;
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
