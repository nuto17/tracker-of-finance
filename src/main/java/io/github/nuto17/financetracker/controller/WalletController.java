package io.github.nuto17.financetracker.controller;

import io.github.nuto17.financetracker.dto.TransactionDto;
import io.github.nuto17.financetracker.dto.WalletDto;
import io.github.nuto17.financetracker.mapper.TransactionMapper;
import io.github.nuto17.financetracker.mapper.WalletMapper;
import io.github.nuto17.financetracker.marks.TransactionType;
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

    @GetMapping("/{walletId}")
    public WalletDto getWalletById(@PathVariable("walletId") @Min(1) Long id) {
        Wallet walletById = walletService.getWalletById(id);
        return walletMapper.toDto(walletById);
    }

    @PostMapping
    public WalletDto createWallet(@RequestBody WalletDto walletDto) {
        Wallet wallet = walletMapper.toModel(walletDto);
        Wallet createdWallet = walletService.createWallet(wallet);
        return walletMapper.toDto(createdWallet);
    }

    @PostMapping(path = "/{walletId}/transactions")
    public WalletDto operationWallet(@PathVariable("walletId") @Min(1) Long id, @RequestBody TransactionDto transactionDto, @RequestParam TransactionType type) {
        return walletMapper.toDto(walletService.operationWallet(transactionMapper.toModelFromParamsTypeAndWalletId(transactionDto, id, type)));
    }

    @GetMapping("/{walletId}/transactions")
    public List<TransactionDto> getAllTransactionsByType(@PathVariable("walletId") @Min(1) Long id,
                                                         @RequestParam(value = "type", required = false) TransactionType type) {
        return transactionMapper.toDto(walletService.getAllTransactionsByWalletIdAndType(id, type));
    }

    @GetMapping("/{walletId}/transactions/sum")
    public BigDecimal getSumCategory(@PathVariable("walletId") @Min(1) Long id, @RequestParam(value = "categoryId", required = true) Long categoryId) {
        return walletService.getSumTransactionsByCategoryId(id, categoryId);
    }

    @GetMapping("/{walletId}/transactions/date")
    public List<TransactionDto> getAllTransactionsByPeriod(@PathVariable("walletId") @Min(1) Long id,
                                                           @RequestParam(value = "startDate", required = true) LocalDate startDate,
                                                           @RequestParam(value = "finishDate", required = true) LocalDate finishDate,
                                                           @RequestParam(value = "type", required = false) TransactionType type) {
        return transactionMapper.toDto(walletService.getTransactionsByPeriodAndType(id, type, startDate, finishDate));
    }

    @GetMapping("/{walletId}/transactions/category")
    public List<TransactionDto> getAllTransactionsByCategoryId(@PathVariable("walletId") @Min((1)) Long walletId,
                                                               @RequestParam(value = "categoryId", required = true) Long categoryId) {
        return transactionMapper.toDto(walletService.getAllTransactionsByCategoryId(walletId, categoryId));
    }
}
