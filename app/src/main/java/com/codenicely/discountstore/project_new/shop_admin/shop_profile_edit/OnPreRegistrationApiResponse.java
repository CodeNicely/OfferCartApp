package com.codenicely.discountstore.project_new.shop_admin.shop_profile_edit;

import com.codenicely.discountstore.project_new.shop_admin.shop_register.data.ShopPreRegistrationData;

/**
 * Created by meghal on 14/4/17.
 */

public interface OnPreRegistrationApiResponse {

    void onSuccess(ShopPreRegistrationData shopPreRegistrationData);
    void onFailure(String message);

}
