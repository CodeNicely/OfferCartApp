package com.codenicely.discountstore.project1.shops.model;

import com.codenicely.discountstore.project1.shops.view.OnShopsReceived;

/**
 * Created by meghal on 22/10/16.
 */

public interface ShopProvider {

    void getShops(String access_token, int category_id, OnShopsReceived onShopsReceived);

}
