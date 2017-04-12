package com.codenicely.discountstore.project_new.shop_admin.shop_signup.view;

import com.codenicely.discountstore.project_new.shop_admin.shop_signup.model.data.CitiesDataDetails;

import java.util.List;

/**
 * Created by ramya on 26/2/17.
 */

public interface ShopSignUpView {
    void shoWProgress(boolean show);
    void showError(String message);
    void setCity(List<CitiesDataDetails> cityList);

}
