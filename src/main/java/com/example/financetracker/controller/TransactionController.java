package com.example.financetracker.controller;

import com.example.financetracker.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/wallets/transactions")
public class TransactionController {

    private final TransactionMapper mapper;
    //TODO get request for transactions
}
