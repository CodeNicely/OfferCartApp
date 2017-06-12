package com.codenicely.brandstore.project.shops.view;

import com.codenicely.brandstore.project.shops.model.data.ShopList;

/**
 * Created by iket on 22/10/16.
 */
public interface OnShopsReceived {
    void onFailure();

    void onSuccess(ShopList shopList);
}
