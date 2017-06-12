package com.codenicely.brandstore.project.shop_admin.shop_register.presenter;

import android.net.Uri;

/**
 * Created by meghal on 11/10/16.
 */

public interface ShopRegisterPresenter {


    /**
     * This function is called to open camera for clicking new image
     */
    void openCamera();

    /**
     * This function s called from view if user chooses to select images already present in gallery
     */
    void openGallery();

    void registerShop(String name, String mobile, String password, String description, String address,
                      String category, String city,Double latitude ,Double longitude,Uri imageUri);

    void requestPreRegistrationDetails();

    void requestCityList(int state_id);
}
