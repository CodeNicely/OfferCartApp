package com.codenicely.discountstore.project1.shops.view;

import com.codenicely.discountstore.project1.shops.model.data.ShopList;

/**
 * Created by iket on 22/10/16.
 */
public interface OnShopsReceived {
    void onFailure();

    void onSuccess(ShopList shopList);
}
