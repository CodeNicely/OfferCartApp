package com.example.aman.offercart_v1.shops.model;

import com.example.aman.offercart_v1.shops.view.OnShopsReceived;

/**
 * Created by meghal on 22/10/16.
 */

public interface ShopProvider {

    void getShops(String category_id, OnShopsReceived onShopsReceived);

}
