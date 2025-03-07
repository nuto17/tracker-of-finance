package com.example.financeTracker.mapper;

import com.example.financeTracker.model.Wallet;
import com.example.financeTracker.modelDto.WalletDto;
import org.mapstruct.Mapper;

@Mapper
public interface WalletMapper {

    Wallet toModel(WalletDto walletDto);
    WalletDto toDto(Wallet wallet);
}
