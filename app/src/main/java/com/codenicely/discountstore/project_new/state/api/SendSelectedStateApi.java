package com.codenicely.discountstore.project_new.state.api;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.state.data.SelectedStateData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ujjwal on 9/6/17.
 */

public interface SendSelectedStateApi {
	@FormUrlEncoded
	@POST(Urls.SEND_STATE)
	Call<SelectedStateData> sendState(@Field("state_id") int state, @Field("access_token") String token);
}
