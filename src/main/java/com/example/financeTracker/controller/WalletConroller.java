package com.example.financeTracker.controller;

import com.example.financeTracker.dto.WalletDto;
import com.example.financeTracker.mapper.WalletMapper;
import com.example.financeTracker.model.Wallet;
import com.example.financeTracker.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @PutMapping("/updateBalance/{id}")
    public WalletDto updateWalletBalanceById(@PathVariable("id") Long id, @RequestBody WalletDto walletDto){
        Wallet walletWithOnlyBalance = mapper.toModelWithOnlyBalance(walletDto);
        Wallet walletById = walletService.getWalletById(id);
        BigDecimal updatedBalance = walletById.getBalance().add(walletWithOnlyBalance.getBalance());
        walletById.setUpdateTime(walletWithOnlyBalance.getUpdateTime());
        walletById.setBalance(updatedBalance);
        Wallet updatedWallet = walletService.updateWallet(walletById, walletById.getId());
        return mapper.toDto(updatedWallet);
    }

    @DeleteMapping("/{id}")
    public void deleteWallet(@PathVariable("id") Long id) {
        walletService.deleteWallet(id);
    }
}
