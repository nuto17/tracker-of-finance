package com.example.financetracker.mapper;

import com.example.financetracker.dto.WalletDto;
import com.example.financetracker.model.Wallet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    Wallet toModel(WalletDto walletDto);
    WalletDto toDto(Wallet wallet);

    List<Wallet> toModel(List<WalletDto> walletsDto);
    List<WalletDto> toDto(List<Wallet> wallets);
}
