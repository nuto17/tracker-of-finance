package com.example.financeTracker.controller;

import com.example.financeTracker.Dto.CategoryDto;
import com.example.financeTracker.mapper.CategoryMapper;
import com.example.financeTracker.model.Category;
import com.example.financeTracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper mapper;

    @GetMapping
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryService.getCategories();
        List<CategoryDto> categoriesDto = mapper.toDto(categories);
        return categoriesDto;
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable("id") Long id) {
        Category categoryById = categoryService.getCategoryById(id);
        CategoryDto categoryByIdDto = mapper.toDto(categoryById);
        return categoryByIdDto;
    }

    @PostMapping
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        Category category = mapper.toModel(categoryDto);
        Category createdCategory = categoryService.createCategory(category);
        CategoryDto createdCategoryDto = mapper.toDto(createdCategory);
        return createdCategoryDto;
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable("id") Long id) {
        Category category = mapper.toModel(categoryDto);
        Category updatedCategory = categoryService.updateCategory(category, id);
        CategoryDto updatedCategoryDto = mapper.toDto(updatedCategory);
        return updatedCategoryDto;
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
    }
}
