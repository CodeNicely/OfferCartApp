package com.codenicely.brandstore.project.state.api;

import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.state.data.StateData;

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
