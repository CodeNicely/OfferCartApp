package com.codenicely.discountstore.project_new.shop_admin.shop_signup.presenter;

/**
 * Created by ramya on 26/2/17.
 */

public interface ShopSignUpPresenter {
    void signUp(String shop_name,String shop_address,String shop_image,int shop_city_id,
                String shop_mobile_number,String shop_email,String shop_password);
}
