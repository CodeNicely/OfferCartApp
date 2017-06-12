package com.codenicely.brandstore.project.shop_admin.payment_shop.model.data;

/**
 * Created by aman on 19/5/17.
 */

public class ShopPaymentData {

    private boolean success;
    private String message;

    private String merchant_id;
    private String order_id;
    private String customer_id;
    private String industry_type_id;
    private String channel_id;
    private String amount;
    private String website;
    private String email;
    private String mobile;
    private String callback_url;
    private String checksum_hash;

    public ShopPaymentData(boolean success, String message, String merchant_id, String order_id, String customer_id, String industry_type_id, String channel_id, String amount, String website, String email, String mobile, String callback_url, String checksum_hash) {
        this.success = success;
        this.message = message;
        this.merchant_id = merchant_id;
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.industry_type_id = industry_type_id;
        this.channel_id = channel_id;
        this.amount = amount;
        this.website = website;
        this.email = email;
        this.mobile = mobile;
        this.callback_url = callback_url;
        this.checksum_hash = checksum_hash;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getIndustry_type_id() {
        return industry_type_id;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public String getAmount() {
        return amount;
    }

    public String getWebsite() {
        return website;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public String getChecksum_hash() {
        return checksum_hash;
    }
}