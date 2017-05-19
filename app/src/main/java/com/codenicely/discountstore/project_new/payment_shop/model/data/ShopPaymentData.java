package com.codenicely.discountstore.project_new.payment_shop.model.data;

/**
 * Created by aman on 19/5/17.
 */

public class ShopPaymentData {

        private boolean success;
        private String message;
        private String mobile;
        private String name;
        private String email;
        private String key;
        private String merchant_id;
        private double amount;
        private String transaction_id;
         private String product_name;

        private String server_hash;

    public ShopPaymentData(boolean success, String message, String mobile, String name, String email,
                           String key, String merchant_id, double amount, String transaction_id, String product_name, String server_hash) {
        this.success = success;
        this.message = message;
        this.mobile = mobile;
        this.name = name;
        this.email = email;
        this.key = key;
        this.merchant_id = merchant_id;
        this.amount = amount;
        this.transaction_id = transaction_id;
        this.product_name = product_name;
        this.server_hash = server_hash;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getMobile() {
        return mobile;
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

    public double getAmount() {
        return amount;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getServer_hash() {
        return server_hash;
    }
}










