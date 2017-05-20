package com.codenicely.discountstore.project_new.shop_admin.shop_register.presenter;

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
                      String category, String city, Uri imageUri);

    void requestPreRegistrationDetails();

}
