package com.example.financeTracker.service;

import com.example.financeTracker.mapper.WalletMapper;
import com.example.financeTracker.model.Wallet;
import com.example.financeTracker.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new EntityNotFoundException("wallet with id doesn't exist"));
        return walletById;
    }

    public Wallet updateWallet(Wallet wallet, Long id) {
        Wallet walletById = getWalletById(id);
        Wallet builedWallet = Wallet.builder()
                .id(walletById.getId())
                .name(wallet.getName())
                .balance(wallet.getBalance())
                .build();
        Wallet savedBuilded = walletRepository.save(builedWallet);
        return savedBuilded;
    }

    public List<Wallet> getWallets(){
        List<Wallet> wallets = walletRepository.findAll();
        return wallets;
    }

    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }
}
