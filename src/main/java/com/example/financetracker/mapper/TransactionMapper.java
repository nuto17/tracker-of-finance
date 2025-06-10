package com.example.financetracker.mapper;

import com.example.financetracker.dto.TransactionDto;
import com.example.financetracker.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {

    Transaction toModel(TransactionDto transactionDto);

    TransactionDto toDto(Transaction transaction);
}