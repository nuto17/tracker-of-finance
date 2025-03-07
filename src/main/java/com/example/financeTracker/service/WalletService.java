package com.example.financeTracker.service;

import com.example.financeTracker.mapper.WalletMapper;
import com.example.financeTracker.model.Wallet;
import com.example.financeTracker.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class WalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper mapper;

    public Wallet saveWallet(Wallet wallet) {
        Wallet savedWallet = walletRepository.save(wallet);
        return savedWallet;
    }

    public Wallet getWalletById(Long id) {
        Wallet walletById = walletRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("wallet with id doesnt exist"));
        return walletById;
    }

    public Wallet updateWallet(Wallet wallet, Long id) {
        Wallet walletById = getWalletById(id);
        Wallet builedWallet = Wallet.builder()
                .id(wallet.getId())
                .balance(wallet.getBalance())
                .build();
        return builedWallet;
    }

    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }
}
