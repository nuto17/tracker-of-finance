package io.github.nuto17.financetracker.controller;

import io.github.nuto17.financetracker.dto.TransactionDto;
import io.github.nuto17.financetracker.dto.WalletDto;
import io.github.nuto17.financetracker.mapper.TransactionMapper;
import io.github.nuto17.financetracker.mapper.WalletMapper;
import io.github.nuto17.financetracker.marks.CategoryRequestType;
import io.github.nuto17.financetracker.marks.TransactionType;
import io.github.nuto17.financetracker.model.Transaction;
import io.github.nuto17.financetracker.model.Wallet;
import io.github.nuto17.financetracker.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallets")
public class WalletController {

    private final WalletService walletService;
    private final WalletMapper walletMapper;
    private final TransactionMapper transactionMapper;

    @GetMapping
    public List<WalletDto> getWallets() {
        List<Wallet> wallets = walletService.getWallets();
        return walletMapper.toDto(wallets);
    }

    @GetMapping("/{id}")
    public WalletDto getWalletById(@PathVariable("id") Long id) {
        Wallet walletById = walletService.getWalletById(id);
        return walletMapper.toDto(walletById);
    }

    @PostMapping
    public WalletDto createWallet(@RequestBody WalletDto walletDto) {
        Wallet wallet = walletMapper.toModel(walletDto);
        Wallet createdWallet = walletService.createWallet(wallet);
        return walletMapper.toDto(createdWallet);
    }

    @PutMapping("/{id}")
    public WalletDto updateWalletById(@PathVariable("id") Long id, @RequestBody WalletDto walletDto) {
        Wallet wallet = walletMapper.toModel(walletDto);
        Wallet updatedWallet = walletService.updateWallet(wallet, id);
        return walletMapper.toDto(updatedWallet);
    }

    @DeleteMapping("/{id}")
    public void deleteWallet(@PathVariable("id") Long id) {
        walletService.deleteWalletById(id);
    }

    @PostMapping(path = "/{id}/transactions")
    public WalletDto operationWallet(@RequestBody TransactionDto transactionDto, @PathVariable Long id, @RequestParam TransactionType type) {
        Transaction transaction = transactionMapper.toModel(transactionDto);
        transaction.setWalletId(id);
        transaction.setType(type);
        return walletMapper.toDto(walletService.operationWallet(transaction));
    }

    @GetMapping("/{id}/transactions")
    public List<TransactionDto> getAllTransactionsByParams(@PathVariable Long id,
                                                           @RequestParam(value = "type", required = false) TransactionType type,
                                                           @RequestParam(value = "firstDate", required = false) LocalDate startDate,
                                                           @RequestParam(value = "secondDate", required = false) LocalDate endDate) {
        return transactionMapper.toDto(walletService.getAllTransactions(id, type, startDate, endDate));
    }

    @GetMapping("/{id}/transactions/{categoryId}")
    public BigDecimal getSumCategoryByWalletId(@PathVariable Long id,@PathVariable Long categoryId, @RequestParam(value = "type", required = false) CategoryRequestType type) {
        if (type!=null){
           return walletService.getSumTransactionsByCategoryId(id, categoryId);
        }
        return BigDecimal.ZERO;

        //TODO прописать на существование walletId, categoryId
    }
}
