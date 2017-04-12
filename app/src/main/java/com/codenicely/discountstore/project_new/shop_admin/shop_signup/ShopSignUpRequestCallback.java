package com.codenicely.discountstore.project_new.shop_admin.shop_signup;

import com.codenicely.discountstore.project_new.shop_admin.shop_signup.model.data.ShopSignUpData;

/**
 * Created by ramya on 26/2/17.
 */

public interface ShopSignUpRequestCallback {
    void onSuccess(ShopSignUpData shopSignUpData);
    void onFailure(String message);
}
