package com.example.financetracker.mapper;

import com.example.financetracker.dto.CategoryDto;
import com.example.financetracker.dto.ExpenseDto;
import com.example.financetracker.dto.ExpenseDtoForOutput;
import com.example.financetracker.dto.WalletDto;
import com.example.financetracker.model.Category;
import com.example.financetracker.model.Expense;
import com.example.financetracker.model.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    @Mapping(target = "id", source = "expenseDto.id")
    @Mapping(target = "amount", source = "expenseDto.amount")
    @Mapping(target = "timeAdded", source = "expenseDto.timeAdded")
    @Mapping(target = "category", source = "expenseDto.categoryId", qualifiedByName = "mapDtoIdToCategory")
    @Mapping(target = "wallet", source = "expenseDto.walletId", qualifiedByName = "mapDtoIdToWallet")
    Expense toModel(ExpenseDto expenseDto);

    @Mapping(target = "id", source = "expense.id")
    @Mapping(target = "amount", source = "expense.amount")
    @Mapping(target = "timeAdded", source = "expense.timeAdded")
    @Mapping(target = "category", source = "expense.category", qualifiedByName = "mapCategoryToDto")
    @Mapping(target = "wallet", source = "expense.wallet", qualifiedByName = "mapWalletToDto")
    ExpenseDtoForOutput toDto(Expense expense);

    List<Expense> toModel(List<ExpenseDto> expensesDto);

    List<ExpenseDtoForOutput> toDto(List<Expense> expenses);


    @Named("mapDtoIdToCategory")
    default Category mapDtoIdToCategory(Long categoryId){
        return Category.builder()
                .id(categoryId)
                .build();
    }

    @Named("mapCategoryToDto")
    default CategoryDto mapCategoryToDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .limit(category.getCategoryLimit())
                .limitStatus(category.getCategoryLimitMark())
                .balance(category.getCategoryBalance())
                .build();
    }

    @Named("mapDtoIdToWallet")
    default Wallet mapDtoIdToWallet(Long walletId){
        return Wallet.builder()
                .id(walletId)
                .build();
    }

    @Named("mapWalletToDto")
    default WalletDto mapWalletToDto(Wallet wallet){
        return WalletDto.builder()
                .name(wallet.getName())
                .balance(wallet.getBalance())
                .id(wallet.getId())
                .updateTime(wallet.getUpdateTime())
                .build();
    }
}
