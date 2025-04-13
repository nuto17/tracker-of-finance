package com.example.financetracker.mapper;

import com.example.financetracker.dto.CategoryDto;
import com.example.financetracker.marks.Enum;
import com.example.financetracker.model.Category;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Named("fullMappingForCreate")
    @Mapping(target = "categoryBalance", source = "balance",qualifiedByName = "mapBalanceDtoToModel")
    @Mapping(target = "categoryLimitMark", source = "categoryDto.limitStatus", qualifiedByName = "mapMarksDtoToModel")
    @Mapping(target = "categoryLimit", source = "categoryDto.limit", qualifiedByName = "mapIncludeEmptyLimit")
    Category toModel(CategoryDto categoryDto);

    @Mapping(target = "balance", source = "category.categoryBalance")
    @Mapping(target = "limitStatus", source = "category.categoryLimitMark")
    @Mapping(target = "limit", source = "category.categoryLimit")
    CategoryDto toDto(Category category);

    @IterableMapping(qualifiedByName = "fullMappingForCreate")
    List<Category> toModel(List<CategoryDto> categoriesDto);

    List<CategoryDto> toDto(List<Category> categories);

    @Named("mapIncludeEmptyLimit")
    default BigDecimal mapIncludeEmptyLimit(BigDecimal limitFromDto){
        if(limitFromDto!=null){
            return limitFromDto;
        }
        return BigDecimal.ZERO;
    }

    @Named("mapBalanceDtoToModel")
    default BigDecimal mapBalanceDtoToModel(BigDecimal balanceDto){
        if(balanceDto!=null){
            return balanceDto;
        }
        return BigDecimal.ZERO;
    }

    @Named("mapMarksDtoToModel")
    default Enum mapMarksDtoToModel(Enum marksDto){
        if(marksDto!=null){
            return marksDto;
        }
        return Enum.GOOD;
    }
}

