package com.example.financetracker.service;

import com.example.financetracker.marks.TransactionType;
import com.example.financetracker.model.Transaction;
import com.example.financetracker.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactionByWalletIdAndType(Long walletId, TransactionType type) {
       return transactionRepository.findAllByWalletIdAndType(walletId,type);
    }
}
