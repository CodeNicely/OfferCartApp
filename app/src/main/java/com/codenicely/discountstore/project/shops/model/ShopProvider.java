package com.codenicely.discountstore.project.shops.model;

import com.codenicely.discountstore.project.shops.view.OnShopsReceived;

/**
 * Created by meghal on 22/10/16.
 */

public interface ShopProvider {

    void getShops(String access_token, String category_id, OnShopsReceived onShopsReceived);

}
