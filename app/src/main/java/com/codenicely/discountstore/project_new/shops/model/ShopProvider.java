package com.codenicely.discountstore.project_new.shops.model;

import com.codenicely.discountstore.project_new.shops.view.OnShopsReceived;

/**
 * Created by meghal on 22/10/16.
 */

public interface ShopProvider {

    void getShops(String access_token, int category_id,Double latitude,Double longitude, OnShopsReceived onShopsReceived);

}
