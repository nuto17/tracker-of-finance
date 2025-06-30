package io.github.nuto17.financetracker.controller;

import io.github.nuto17.financetracker.dto.TransactionDto;
import io.github.nuto17.financetracker.dto.WalletDto;
import io.github.nuto17.financetracker.mapper.TransactionMapper;
import io.github.nuto17.financetracker.mapper.WalletMapper;
import io.github.nuto17.financetracker.marks.TransactionType;
import io.github.nuto17.financetracker.model.Transaction;
import io.github.nuto17.financetracker.model.Wallet;
import io.github.nuto17.financetracker.service.WalletService;
import jakarta.validation.constraints.Min;
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
    public WalletDto getWalletById(@PathVariable("id") @Min(1) Long id) {
        Wallet walletById = walletService.getWalletById(id);
        return walletMapper.toDto(walletById);
    }

    @PostMapping
    public WalletDto createWallet(@RequestBody WalletDto walletDto) {
        Wallet wallet = walletMapper.toModel(walletDto);
        Wallet createdWallet = walletService.createWallet(wallet);
        return walletMapper.toDto(createdWallet);
    }

    @DeleteMapping("/{id}")
    public void deleteWallet(@PathVariable("id") @Min(1) Long id) {
        walletService.deleteWalletById(id);
    }

    @PostMapping(path = "/{id}/transactions")
    public WalletDto operationWallet(@PathVariable("id") @Min(1) Long id, @RequestBody TransactionDto transactionDto, @RequestParam TransactionType type) {
        Transaction transaction = transactionMapper.toModelFromParamsTypeAndWalletId(transactionDto, id, type);
        return walletMapper.toDto(walletService.operationWallet(transaction));
    }

    @GetMapping("/{id}/transactions")
    public List<TransactionDto> getAllTransactionsByParams(@PathVariable("id") @Min(1) Long id,
                                                           @RequestParam(value = "type", required = false) TransactionType type,
                                                           @RequestParam(value = "categoryId", required = false) Long categoryId) {
        return transactionMapper.toDto(walletService.getAllTransactionsByWalletIdAndType(id, type));
    }

    @GetMapping("/{id}/transactions/sum")
    public BigDecimal getSumCategoryByWalletId(@PathVariable("id") @Min(1) Long id, @RequestParam(value = "categoryId", required = true) Long categoryId) {
        return walletService.getSumTransactionsByCategoryId(id, categoryId);
    }

    @GetMapping("/{id}/transactions/date")
    public List<TransactionDto> getAllTransactionsByPeriod(@PathVariable("id") @Min(1) Long id,
                                                           @RequestParam(value = "firstDate", required = true) LocalDate firstDate,
                                                           @RequestParam(value = "secondDate", required = true) LocalDate secondDate,
                                                           @RequestParam(value = "type", required = false) TransactionType type) {
        return transactionMapper.toDto(walletService.getTransactionsByPeriodAndType(id, type, firstDate, secondDate));
    }
}
