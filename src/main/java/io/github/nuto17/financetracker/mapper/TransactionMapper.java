package io.github.nuto17.financetracker.mapper;

import io.github.nuto17.financetracker.dto.TransactionDto;
import io.github.nuto17.financetracker.enumFin.TransactionType;
import io.github.nuto17.financetracker.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {

    Transaction toModel(TransactionDto transactionDto);
    List<Transaction> toModel(List<TransactionDto> transactionsDto);

    TransactionDto toDto(Transaction transaction);
    List<TransactionDto> toDto(List<Transaction> transactions);

    @Mapping(target = "type", source = "typeFromParams")
    @Mapping(target = "walletId", source = "walletIdFromParams")
    Transaction toModelFromParamsTypeAndWalletId(TransactionDto transactionDto, Long walletIdFromParams, TransactionType typeFromParams);
}