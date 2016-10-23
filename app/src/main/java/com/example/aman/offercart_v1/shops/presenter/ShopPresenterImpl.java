package com.example.aman.offercart_v1.shops.presenter;

import com.example.aman.offercart_v1.shops.model.ShopProvider;
import com.example.aman.offercart_v1.shops.model.data.ShopList;
import com.example.aman.offercart_v1.shops.view.OnShopsReceived;
import com.example.aman.offercart_v1.shops.view.ShopView;

/**
 * Created by iket on 23/10/16.
 */
public class ShopPresenterImpl implements ShopPresenter {
    private ShopView shopView;
    private ShopProvider shopProvider;

    public ShopPresenterImpl(ShopView shopView, ShopProvider shopProvider) {
        this.shopView = shopView;
        this.shopProvider = shopProvider;
    }

    @Override
    public void getShops(String category_id) {
        shopView.showLoading(true);
        shopProvider.getShops(category_id, new OnShopsReceived() {
            @Override
            public void onFailure() {
                shopView.showLoading(false);
                shopView.showMessage("Error");
            }

            @Override
            public void onSuccess(ShopList shopList) {
                shopView.showLoading(false);
                shopView.OnShopsDataReceived(shopList.getShopDatas());

            }
        });

    }
}
