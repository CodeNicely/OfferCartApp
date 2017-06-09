package com.codenicely.discountstore.project_new.state.api;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.state.data.StateData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ujjwal on 9/6/17.
 */
public interface StateRequestApi {

	@GET(Urls.REQUEST_STATE)
	Call<StateData> getStates(@Query("access_token") String token);

}
