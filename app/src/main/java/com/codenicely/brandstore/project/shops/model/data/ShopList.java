package com.codenicely.brandstore.project.shops.model.data;

import java.util.List;

/**
 * Created by iket on 22/10/16.
 */
public class ShopList {
    private List<ShopData> shopDatas;
    private boolean success;
    private String message;

    public ShopList(List<ShopData> shopDatas, boolean success, String message) {
        this.shopDatas = shopDatas;
        this.success = success;
        this.message = message;
    }

    public List<ShopData> getShopDatas() {
        return shopDatas;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
