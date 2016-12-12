package com.codenicely.discountstore.project1.payment.model.data;

/**
 * Created by meghal on 1/12/16.
 */

public class PaymentData {

    private boolean success;
    private String message;
    private String mobile;
    private String product_name;
    private String name;
    private String email;
    private String key;
    private String merchant_id;
    private double amount;
    private String transaction_id;


    private String server_hash;

    public PaymentData(boolean success, String message, double amount, String transaction_id, String mobile, String product_name, String name, String email, String key, String merchant_id, String server_hash) {
        this.success = success;
        this.message = message;
        this.amount = amount;
        this.transaction_id = transaction_id;
        this.mobile = mobile;
        this.product_name = product_name;
        this.name = name;
        this.email = email;
        this.key = key;
        this.merchant_id = merchant_id;
        this.server_hash = server_hash;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public String getMobile() {
        return mobile;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getKey() {
        return key;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public String getServer_hash() {
        return server_hash;
    }
}
