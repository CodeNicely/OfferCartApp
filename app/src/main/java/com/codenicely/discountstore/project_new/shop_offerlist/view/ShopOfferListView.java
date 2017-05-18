package com.codenicely.discountstore.project_new.shop_offerlist.view;

import com.codenicely.discountstore.project_new.shop_offerlist.model.data.ShopOfferListData;
import com.codenicely.discountstore.project_new.shop_offerlist.model.data.ShopOfferListDetails;

/**
 * Created by aman on 16/5/17.
 */

public interface ShopOfferListView {
    void showProgressBar(boolean show);
    void showMessage(String error);
    void setData(ShopOfferListData shopOfferListData);
}
