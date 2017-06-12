package com.codenicely.brandstore.project.shops.view;

import com.codenicely.brandstore.project.shops.model.data.ShopData;

import java.util.List;

/**
 * Created by iket on 22/10/16.
 */
public interface ShopView {
    void showLoading(boolean show);

    void showMessage(String message);

    void OnShopsDataReceived(List<ShopData> shopDataList);
}
