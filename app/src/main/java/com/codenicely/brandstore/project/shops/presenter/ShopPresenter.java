package com.codenicely.brandstore.project.shops.presenter;

/**
 * Created by iket on 23/10/16.
 */
public interface ShopPresenter {
    void getShops(String access_token, int category_id,Double latitude,Double longitude);
}
