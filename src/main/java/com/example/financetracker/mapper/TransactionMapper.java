package com.example.financetracker.mapper;

import com.example.financetracker.dto.TransactionDto;
import com.example.financetracker.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {

    Transaction toModel(TransactionDto transactionDto);
    List<Transaction> toModel(List<TransactionDto> transactionsDto);

    TransactionDto toDto(Transaction transaction);
    List<TransactionDto> toDto(List<Transaction> transactions);
}