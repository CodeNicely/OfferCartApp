package com.example.aman.offercart_v1.shops.model.data;

import java.util.List;

/**
 * Created by iket on 22/10/16.
 */
public class ShopList {
    private List<ShopData>shopDatas;

    public ShopList(List<ShopData> shopDatas) {
        this.shopDatas = shopDatas;
    }

    public List<ShopData> getShopDatas() {
        return shopDatas;
    }
}
