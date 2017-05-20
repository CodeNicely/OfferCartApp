package com.codenicely.discountstore.project_new.shop_admin.shop_profile_edit.model;

import android.net.Uri;

import com.codenicely.discountstore.project_new.shop_admin.shop_profile_edit.data.ShopEditData;
import com.codenicely.discountstore.project_new.shop_admin.shop_register.OnPreRegistrationApiResponse;

import java.io.IOException;

import rx.Observable;

/**
 * Created by ujjwal on 17/5/17.
 */
public interface EditShopProfileHelper {
	Observable<ShopEditData> editShop(String shop_access_token,
									  String name,
									  String description,
									  String address,
									  String category,
									  String city,
									  Uri imageUri) throws IOException;


	void requestPreRegistrationDetails(OnPreRegistrationApiResponse onPreRegistrationApiResponse);

}
