package com.example.financeTracker.mapper;

import com.example.financeTracker.dto.CategoryDto;
import com.example.financeTracker.dto.ExpenseDto;
import com.example.financeTracker.dto.WalletDto;
import com.example.financeTracker.model.Category;
import com.example.financeTracker.model.Expense;
import com.example.financeTracker.model.Wallet;
import com.example.financeTracker.service.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    @Mapping(target = "id", source = "expenseDto.id")
    @Mapping(target = "amount", source = "expenseDto.amount")
    @Mapping(target = "timeAdded", source = "expenseDto.timeAdded")
    @Mapping(target = "category", source = "expenseDto.category", qualifiedByName = "mapDtoIdToCategory")
    @Mapping(target = "wallet", source = "expenseDto.wallet", qualifiedByName = "mapDtoIdToWallet")
    Expense toModel(ExpenseDto expenseDto);

    @Mapping(target = "id", source = "expense.id")
    @Mapping(target = "amount", source = "expense.amount")
    @Mapping(target = "timeAdded", source = "expense.timeAdded")
    @Mapping(target = "category", source = "expense.category", qualifiedByName = "mapCategoryToDtoName")
    @Mapping(target = "wallet", source = "expense.wallet", qualifiedByName = "mapWalletToDtoName")
    ExpenseDto toDto(Expense expense);

    List<Expense> toModel(List<ExpenseDto> expensesDto);

    List<ExpenseDto> toDto(List<Expense> expenses);

    @Named("mapDtoIdToCategory")
    default Category mapDtoIdToCategory(CategoryDto categoryDto) {
        Category categoryId = Category.builder()
                .id(categoryDto.getId())
                .build();
        return categoryId;
    }

    @Named("mapCategoryToDtoName")
    default CategoryDto mapCategoryToDtoName(Category category) {
        CategoryDto categoryDtoName = CategoryDto.builder()
                .name(category.getName())
                .id(category.getId())
                .build();
        return categoryDtoName;
    }

    @Named("mapDtoIdToWallet")
    default Wallet mapDtoIdToWallet(WalletDto walletDto){
        Wallet walletId = Wallet.builder()
                .id(walletDto.getId())
                .build();
        return walletId;
    }

    @Named("mapWalletToDtoName")
    default WalletDto mapWalletToDtoName(Wallet wallet){
        WalletDto walletDtoName = WalletDto.builder()
                .name(wallet.getName())
                .build();
        return walletDtoName;
    }
}
