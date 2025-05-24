package com.example.financetracker.controller;

import com.example.financetracker.dto.CategoryDto;
import com.example.financetracker.mapper.CategoryMapper;
import com.example.financetracker.model.Category;
import com.example.financetracker.service.CategoryService;
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
        return mapper.toDto(categories);
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable("id") Long id) {
        Category categoryById = categoryService.getCategoryById(id);
        return mapper.toDto(categoryById);
    }

    @PostMapping
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        Category category = mapper.toModel(categoryDto);
        Category createdCategory = categoryService.createCategory(category);
        return mapper.toDto(createdCategory);
    }

//    @PutMapping("/{id}")
//    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable("id") Long id) {
//        Category category = mapper.toModel(categoryDto);
//        Category updatedCategory = categoryService.updateCategory(category, id);
//        return mapper.toDto(updatedCategory);
//    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
    }
}
