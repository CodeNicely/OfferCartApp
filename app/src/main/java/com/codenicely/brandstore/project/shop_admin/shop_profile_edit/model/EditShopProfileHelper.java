package com.codenicely.brandstore.project.shop_admin.shop_profile_edit.model;

import android.net.Uri;

import com.codenicely.brandstore.project.shop_admin.shop_profile_edit.data.ShopEditData;
import com.codenicely.brandstore.project.shop_admin.shop_register.OnCitiesReceived;
import com.codenicely.brandstore.project.shop_admin.shop_register.OnPreRegistrationApiResponse;

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
									  String city,Double latitude,Double longitude,
									  Uri imageUri) throws IOException;


	void requestPreRegistrationDetails(OnPreRegistrationApiResponse onPreRegistrationApiResponse);

	void requestCityList(int state_id, OnCitiesReceived onCitiesReceived);
}
