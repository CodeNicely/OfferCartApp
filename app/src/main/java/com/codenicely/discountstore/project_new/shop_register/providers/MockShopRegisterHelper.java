package com.codenicely.discountstore.project_new.shop_register.providers;

import android.net.Uri;

import com.codenicely.discountstore.project_new.shop_register.OnPreRegistrationApiResponse;
import com.codenicely.discountstore.project_new.shop_register.data.ShopRegisterData;

import java.io.IOException;

import rx.Observable;

/**
 * Created by meghal on 11/10/16.
 */

public class MockShopRegisterHelper implements ShopRegisterHelper {

    @Override
    public Observable<ShopRegisterData> registerShop(String name, String mobile, String password, String description, String address, String category, String city, Uri imageUri) throws IOException {
        return null;
    }

    @Override
    public void requestPreRegistrationDetails(OnPreRegistrationApiResponse onPreRegistrationApiResponse) {
        // do nothing
    }
}
