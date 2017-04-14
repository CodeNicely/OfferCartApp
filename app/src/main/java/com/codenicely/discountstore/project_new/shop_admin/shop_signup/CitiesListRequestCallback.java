package com.codenicely.discountstore.project_new.shop_admin.shop_signup;

import com.codenicely.discountstore.project_new.shop_admin.shop_signup.model.data.CitiesDataDetails;

/**
 * Created by ramya on 26/2/17.
 */

public interface CitiesListRequestCallback {
    void onSuccess(CitiesDataDetails citiesDataDetails);
    void onFailure(String message);
}
