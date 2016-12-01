package com.codenicely.discountstore.project1.shops.view;

import com.codenicely.discountstore.project1.shops.model.data.ShopData;

import java.util.List;

/**
 * Created by iket on 22/10/16.
 */
public interface ShopView {
    void showLoading(boolean show);

    void showMessage(String message);

    void OnShopsDataReceived(List<ShopData> shopDataList);
}
