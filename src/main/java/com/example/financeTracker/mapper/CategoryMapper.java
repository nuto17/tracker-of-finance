package com.example.financeTracker.mapper;

import com.example.financeTracker.model.Category;
import com.example.financeTracker.modelDto.CategoryDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toModel(CategoryDto categoryDto);
    CategoryDto toDto(Category category);

    List<Category> toModel(List<CategoryDto> categoriesDto);
    List<CategoryDto> toDto(List<Category> categories);
}
