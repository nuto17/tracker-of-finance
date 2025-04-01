package com.example.financeTracker.mapper;


import com.example.financeTracker.dto.WalletDto;
import com.example.financeTracker.model.Wallet;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    @Named("fullMappingForCreate")
    Wallet toModel(WalletDto walletDto);

    WalletDto toDto(Wallet wallet);

    @IterableMapping(qualifiedByName = "fullMappingForCreate")
    List<Wallet> toModel(List<WalletDto> walletsDto);

    List<WalletDto> toDto(List<Wallet> wallets);
}
