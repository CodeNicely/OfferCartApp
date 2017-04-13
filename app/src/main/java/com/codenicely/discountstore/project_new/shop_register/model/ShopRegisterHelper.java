package com.codenicely.discountstore.project_new.shop_register.model;

import android.net.Uri;

import com.codenicely.discountstore.project_new.shop_register.model.data.ShopRegisterData;

import java.io.IOException;

import rx.Observable;

/**
 * Created by meghal on 11/10/16.
 */

public interface ShopRegisterHelper {


    Observable<ShopRegisterData> uploadSpot(String name, String mobile, String email,
                                            String location, String description, Uri imageUri) throws IOException;

}
