package com.example.financetracker.mapper;

import com.example.financetracker.dto.TransactionDto;
import com.example.financetracker.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction toModel(TransactionDto transactionDto);

    TransactionDto toDto(Transaction transaction);

    @Mapping(target = "startDate", source = "startDate", ignore = true)
    @Mapping(target = "lastDate", source = "lastDate", ignore = true)
    @Mapping(target = "timeAdded", source = "timeAdded")
    Transaction toModelForOperation(TransactionDto transactionDto);

    @Mapping(target = "startDate", source = "startDate", ignore = true)
    @Mapping(target = "lastDate", source = "lastDate", ignore = true)
    @Mapping(target = "timeAdded", source = "timeAdded")
    TransactionDto toDtoForOpearation(Transaction transaction);
}
