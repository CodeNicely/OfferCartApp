package com.codenicely.discountstore.project1.wallet.model.data;

/**
 * Created by iket on 20/10/16.
 */
public class WalletData {
    private String balance;
    private String message;
    private boolean success;

    public WalletData(String balance, String message, boolean success) {
        this.balance = balance;
        this.message = message;
        this.success = success;
    }

    public String getBalance() {
        return balance;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}

