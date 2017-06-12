package com.codenicely.brandstore.project.shop_admin.shop_offerlist.model;

import android.os.Handler;

import com.codenicely.brandstore.project.shop_admin.shop_offerlist.ShopOfferCallBack;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.ShopOfferDeleteCallBack;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.model.data.ShopOfferDeleteData;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.model.data.ShopOfferListData;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.model.data.ShopOfferListDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 18/5/17.
 */

public class MockShopOfferListProvider implements ShopOfferListProvider {


    @Override
    public void requestShopOffer(String access_token, final ShopOfferCallBack shopOfferCallBack) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                shopOfferCallBack.onSuccess(getMockShopData());
            }
        },500);
    }

    @Override
    public void delete(String access_token, int offer_id, final ShopOfferDeleteCallBack shopOfferDeleteCallBack) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                shopOfferDeleteCallBack.onSuccess(getMockShopDeleteData());
            }
        },500);
    }




    public ShopOfferListData getMockShopData()
    {

        List<ShopOfferListDetails>shopOfferListDetailses  = new ArrayList<>();


        for(int i=0;i<5;i++)
        {
    /*        ShopOfferListDetails shopOfferListDetails = new ShopOfferListDetails(1,"dv","vdv","vsdv","vdsv","vdv","dv","vfv");
            shopOfferListDetailses.add(shopOfferListDetails);
    */    }

        ShopOfferListData shopOfferListData = new ShopOfferListData(true,"List Received","fhd","dv","Register Now",shopOfferListDetailses);
        return shopOfferListData;
    }



    public ShopOfferDeleteData getMockShopDeleteData()
    {
        ShopOfferDeleteData shopOfferDeleteData = new ShopOfferDeleteData(true,"Data Received");
        return shopOfferDeleteData;
    }
}




