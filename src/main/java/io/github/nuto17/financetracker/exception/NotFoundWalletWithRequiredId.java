package io.github.nuto17.financetracker.exception;

public class NotFoundWalletWithRequiredId extends RuntimeException {
    public NotFoundWalletWithRequiredId(Long walletId) {
        super("Wallet with required id=" + walletId + " doesn't exist");
    }
}
