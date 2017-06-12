package com.codenicely.brandstore.project.shop_admin.shop_offerlist.view;

import com.codenicely.brandstore.project.shop_admin.shop_offerlist.model.data.ShopOfferListData;

/**
 * Created by aman on 16/5/17.
 */

public interface ShopOfferListView {
    void showProgressBar(boolean show);
    void showMessage(String error);
    void setData(ShopOfferListData shopOfferListData);
    void onDeleteSuccessful();
}
