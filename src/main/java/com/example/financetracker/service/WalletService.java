package com.example.financetracker.service;

import com.example.financetracker.model.Wallet;
import com.example.financetracker.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public List<Wallet> getWallets() {
        return walletRepository.findAll();
    }

    public Wallet getWalletById(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("wallet with id doesn't exist"));
        return walletById;
    }

    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public Wallet updateWallet(Wallet wallet, Long id) {
        Wallet walletById = getWalletById(id);
        Wallet builedWallet = Wallet.builder()
                .id(walletById.getId())
                .name(wallet.getName())
                .balance(wallet.getBalance())
                .updateTime(wallet.getUpdateTime())
                .build();
        return walletRepository.save(builedWallet);
    }

    public void deleteWalletById(Long id) {
        walletRepository.deleteById(id);

    }
}