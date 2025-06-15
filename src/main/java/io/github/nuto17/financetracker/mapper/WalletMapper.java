package io.github.nuto17.financetracker.mapper;

import io.github.nuto17.financetracker.dto.WalletDto;
import io.github.nuto17.financetracker.model.Wallet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    Wallet toModel(WalletDto walletDto);
    WalletDto toDto(Wallet wallet);

    List<Wallet> toModel(List<WalletDto> walletsDto);
    List<WalletDto> toDto(List<Wallet> wallets);
}
