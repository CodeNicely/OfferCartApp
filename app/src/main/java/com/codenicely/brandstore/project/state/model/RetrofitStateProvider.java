package com.codenicely.brandstore.project.state.model;


import com.codenicely.brandstore.project.helper.Urls;
import com.codenicely.brandstore.project.state.OnStateSent;
import com.codenicely.brandstore.project.state.OnStatesReceived;
import com.codenicely.brandstore.project.state.api.SendSelectedStateApi;
import com.codenicely.brandstore.project.state.api.StateRequestApi;
import com.codenicely.brandstore.project.state.data.SelectedStateData;
import com.codenicely.brandstore.project.state.data.StateData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ujjwal on 9/6/17.
 */
public class RetrofitStateProvider implements StateProvider {

	private StateRequestApi stateRequestApi;
	private SendSelectedStateApi sendSelectedStateApi;

	public RetrofitStateProvider() {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
		Gson gson = new GsonBuilder()
							.setLenient()
							.create();
		Retrofit retrofit = new Retrofit.Builder()
									.baseUrl(Urls.BASE_URL)
									.addConverterFactory(GsonConverterFactory.create(gson))
									.client(client)
									.build();
		stateRequestApi = retrofit.create(StateRequestApi.class);
		sendSelectedStateApi = retrofit.create(SendSelectedStateApi.class);

	}

	@Override
	public void requestState(String access_token, final OnStatesReceived onStatesReceived) {
		Call<StateData> call = stateRequestApi.getStates(access_token);
		call.enqueue(new Callback<StateData>() {

			@Override
			public void onResponse(Call<StateData> call, Response<StateData> response) {
				onStatesReceived.onSuccess(response.body());
			}

			@Override
			public void onFailure(Call<StateData> call, Throwable t) {
				t.printStackTrace();
				onStatesReceived.onFailure();
			}
		});
	}

	@Override
	public void sendSelectedState(int state_id, String access_token, final OnStateSent onStateSent) {
		Call<SelectedStateData> call = sendSelectedStateApi.sendState(state_id,access_token);
		call.enqueue(new Callback<SelectedStateData>() {
			@Override
			public void onResponse(Call<SelectedStateData> call, Response<SelectedStateData> response) {
				onStateSent.onSuccess(response.body());
			}

			@Override
			public void onFailure(Call<SelectedStateData> call, Throwable t) {
				t.printStackTrace();
				onStateSent.onFailure();
			}
		});
	}

}
