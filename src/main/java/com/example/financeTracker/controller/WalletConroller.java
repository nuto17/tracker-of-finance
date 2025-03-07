package com.example.financeTracker.controller;

import com.example.financeTracker.mapper.WalletMapper;
import com.example.financeTracker.model.Wallet;
import com.example.financeTracker.modelDto.WalletDto;
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
    public List<WalletDto> getWallets(){
        List<Wallet> wallets = walletService.getWallets();
        List<WalletDto> walletsDto = mapper.toDto(wallets);
        return walletsDto;
    }

    @PutMapping("/{id}")
    public WalletDto updateWallet(@PathVariable("id") Long id,@RequestBody WalletDto walletDto){
        Wallet wallet = mapper.toModel(walletDto);
        Wallet updatedWallet = walletService.updateWallet(wallet, id);
        WalletDto updatedWalletDto = mapper.toDto(updatedWallet);
        return updatedWalletDto;
    }

    @PostMapping
    public WalletDto saveWallet(@RequestBody WalletDto walletDto){
        Wallet wallet = mapper.toModel(walletDto);
        Wallet savedWallet = walletService.saveWallet(wallet);
        WalletDto savedWalletDto = mapper.toDto(savedWallet);
        return savedWalletDto;
    }

    @DeleteMapping("/{id}")
    public void deleteWallet(@PathVariable("id") Long id){
        walletService.deleteWallet(id);
    }
}
