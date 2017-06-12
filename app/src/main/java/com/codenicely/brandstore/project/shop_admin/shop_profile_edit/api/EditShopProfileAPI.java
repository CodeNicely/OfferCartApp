package com.codenicely.brandstore.project.shop_admin.shop_profile_edit.api;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.shop_admin.shop_profile_edit.data.ShopEditData;
import com.codenicely.brandstore.project.shop_admin.shop_register.data.ShopPreRegistrationData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by ujjwal on 17/5/17.
 */
public interface EditShopProfileAPI {
	@Multipart
	@POST(Urls.EDIT_SHOP_PROFILE)
	Observable<ShopEditData> requestShopEdit(
		@Part("shop_access_token") RequestBody access_token,

		@Part("name") RequestBody name,
		@Part("description") RequestBody description,
		@Part("address") RequestBody address,
		@Part("category") RequestBody category,
		@Part("city") RequestBody city,
		@Part("latitude") RequestBody latitude,
		@Part("longitude") RequestBody longitude,
		@Part MultipartBody.Part image);

	@GET(Urls.SUB_URL_PREREGISTER_DATA_SHOP)
	Call<ShopPreRegistrationData> requestPreRegisterData();

}
