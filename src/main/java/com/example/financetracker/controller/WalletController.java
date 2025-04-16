package com.example.financetracker.controller;

import com.example.financetracker.dto.WalletDto;
import com.example.financetracker.mapper.WalletMapper;
import com.example.financetracker.model.Wallet;
import com.example.financetracker.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallets")
public class WalletController {

    private final WalletService walletService;
    private final WalletMapper mapper;

    @GetMapping
    public List<WalletDto> getWallets() {
        List<Wallet> wallets = walletService.getWallets();
        return mapper.toDto(wallets);
    }

    @GetMapping("/{id}")
    public WalletDto getWalletById(@PathVariable("id") Long id) {
        Wallet walletById = walletService.getWalletById(id);
        return mapper.toDto(walletById);
    }

    @PostMapping
    public WalletDto createWallet(@RequestBody WalletDto walletDto) {
        Wallet wallet = mapper.toModel(walletDto);
        Wallet createdWallet = walletService.createWallet(wallet);
        return mapper.toDto(createdWallet);
    }

    @PutMapping("/{id}")
    public WalletDto updateWalletById(@PathVariable("id") Long id, @RequestBody WalletDto walletDto) {
        Wallet wallet = mapper.toModel(walletDto);
        Wallet updatedWallet = walletService.updateWallet(wallet, id);
        return mapper.toDto(updatedWallet);
    }

    @DeleteMapping("/{id}")
    public void deleteWallet(@PathVariable("id") Long id) {
        walletService.deleteWalletById(id);
    }
}

