package com.codenicely.discountstore.project_new.shop_admin.shop_signup.presenter;

import com.codenicely.discountstore.project_new.shop_admin.shop_signup.model.ShopSignUpHelper;
import com.codenicely.discountstore.project_new.shops.view.ShopView;

/**
 * Created by ramya on 26/2/17.
 */

public class ShopSignUpPresenterImplementation implements ShopSignUpPresenter{
    private ShopView shopView;
    private ShopSignUpHelper shopSignUpHelper;

    public ShopSignUpPresenterImplementation(ShopView shopView, ShopSignUpHelper shopSignUpHelper) {
        this.shopView = shopView;
        this.shopSignUpHelper = shopSignUpHelper;
    }

    @Override
    public void signUp(String shop_name, String shop_address, String shop_image, int shop_city_id, String shop_mobile_number, String shop_email, String shop_password) {

    }
}
