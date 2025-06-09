package com.example.financetracker.controller;

import com.example.financetracker.dto.TransactionDto;
import com.example.financetracker.mapper.TransactionMapper;
import com.example.financetracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallets/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper mapper;

//    @GetMapping
//    public List<TransactionDto> getTransactionsDto(){
//        //TODO get request for transactions
//    };
    @PostMapping
    public TransactionDto depositWallet(TransactionDto transactionDto){
        return mapper.toDtoForDeposit(transactionService.depositToWallet(mapper.toModelForDeposit(transactionDto)));
    }

}
