package com.example.financeTracker.mapper;

import com.example.financeTracker.model.Wallet;
import com.example.financeTracker.modelDto.WalletDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    Wallet toModel(WalletDto walletDto);
    WalletDto toDto(Wallet wallet);

    List<Wallet> toModel(List<WalletDto> walletsDtos);
    List<WalletDto> toDto(List<Wallet> wallets);
}
