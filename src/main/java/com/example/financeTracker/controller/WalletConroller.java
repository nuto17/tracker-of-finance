package com.example.financeTracker.controller;

import com.example.financeTracker.mapper.WalletMapper;
import com.example.financeTracker.model.Wallet;
import com.example.financeTracker.Dto.WalletDto;
import com.example.financeTracker.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallets")
public class WalletConroller {

    private final WalletService walletService;
    private final WalletMapper mapper;

    @GetMapping
    public List<WalletDto> getWallets() {
        List<Wallet> wallets = walletService.getWallets();
        List<WalletDto> walletsDto = mapper.toDto(wallets);
        return walletsDto;
    }

    @GetMapping("/{id}")
    public WalletDto getWalletById(@PathVariable("id") Long id) {
        Wallet walletById = walletService.getWalletById(id);
        WalletDto walletByIdDto = mapper.toDto(walletById);
        return walletByIdDto;
    }

    @PostMapping
    public WalletDto createWallet(@RequestBody WalletDto walletDto) {
        Wallet wallet = mapper.toModel(walletDto);
        Wallet createdWallet = walletService.createWallet(wallet);
        WalletDto createddWalletDto = mapper.toDto(createdWallet);
        return createddWalletDto;
    }

    @PutMapping("/{id}")
    public WalletDto updateWallet(@PathVariable("id") Long id, @RequestBody WalletDto walletDto) {
        Wallet wallet = mapper.toModel(walletDto);
        Wallet updatedWallet = walletService.updateWallet(wallet, id);
        WalletDto updatedWalletDto = mapper.toDto(updatedWallet);
        return updatedWalletDto;
    }

    @DeleteMapping("/{id}")
    public void deleteWallet(@PathVariable("id") Long id) {
        walletService.deleteWallet(id);
    }
}
