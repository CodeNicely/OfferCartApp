package com.codenicely.discountstore.project_new.wallet.model.data;

/**
 * Created by iket on 20/10/16.
 */
public class WalletData {
    private int balance;
    private String message;
    private boolean success;

    public WalletData(int balance, String message, boolean success) {
        this.balance = balance;
        this.message = message;
        this.success = success;
    }

    public int getBalance() {
        return balance;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}

