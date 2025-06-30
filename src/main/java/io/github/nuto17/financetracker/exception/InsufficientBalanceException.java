package io.github.nuto17.financetracker.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("Balance can not be less than amount");
    }
}
