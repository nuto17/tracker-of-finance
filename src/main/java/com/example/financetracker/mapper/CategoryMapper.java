package com.example.financetracker.mapper;

import com.example.financetracker.dto.CategoryDto;
import com.example.financetracker.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toModel(CategoryDto categoryDto);
    CategoryDto toDto(Category category);

    List<Category> toModel(List<CategoryDto> categoriesDto);
    List<CategoryDto> toDto(List<Category> categories);

    //TODO маппер для обновления (учитывать неизменность полей)
}

