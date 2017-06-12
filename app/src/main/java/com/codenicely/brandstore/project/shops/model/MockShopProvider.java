package com.codenicely.brandstore.project.shops.model;

import com.codenicely.brandstore.project.shops.model.data.ShopData;
import com.codenicely.brandstore.project.shops.model.data.ShopList;
import com.codenicely.brandstore.project.shops.view.OnShopsReceived;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iket on 22/10/16.
 */
public class MockShopProvider implements ShopProvider {

    @Override
    public void getShops(String access_token, int category_id,Double latitude,Double longitude, OnShopsReceived onShopsReceived) {

        List<ShopData> shopDataList = new ArrayList<>();
        ShopData entry1 = new ShopData(1, "Jindal Fashion", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpkWkhbFVadw7jgaAX7VmZ0bpmLIRjHyv1WheK9k_i61HnciixRQ", "Opp.SBI Bank,Pandri,Raipur", (float) 8.579);
        shopDataList.add(entry1);
        shopDataList.add(entry1);
        shopDataList.add(entry1);

        ShopList shopList = new ShopList(shopDataList, true, "Success");
        onShopsReceived.onSuccess(shopList);


    }

}
