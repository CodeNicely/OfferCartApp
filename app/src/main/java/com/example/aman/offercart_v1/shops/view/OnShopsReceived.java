package com.example.aman.offercart_v1.shops.view;

import com.example.aman.offercart_v1.shops.model.data.ShopData;

import java.util.List;

/**
 * Created by iket on 22/10/16.
 */
public interface OnShopsReceived {
    void onFailure();
    void onSuccess(List<ShopData>shopDatas);
}
