package com.codenicely.brandstore.project.shops.model;

import com.codenicely.brandstore.project.shops.view.OnShopsReceived;

/**
 * Created by meghal on 22/10/16.
 */

public interface ShopProvider {

    void getShops(String access_token, int category_id,Double latitude,Double longitude, OnShopsReceived onShopsReceived);

}
