package com.example.financeTracker.mapper;

import com.example.financeTracker.dto.CategoryDto;
import com.example.financeTracker.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "categoryBalance", constant = "0")
    Category toModel(CategoryDto categoryDto);

    CategoryDto toDto(Category category);

    List<Category> toModel(List<CategoryDto> categoriesDto);

    List<CategoryDto> toDto(List<Category> categories);
}
