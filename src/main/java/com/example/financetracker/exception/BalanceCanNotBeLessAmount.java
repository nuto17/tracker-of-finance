package com.example.financetracker.exception;

public class BalanceCanNotBeLessAmount extends RuntimeException {
    public BalanceCanNotBeLessAmount() {
        super("Balance can not be less than amount");
    }
}
