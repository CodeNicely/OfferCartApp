package com.codenicely.discountstore.project_new.state.presenter;

import com.codenicely.discountstore.project_new.state.OnStateSent;
import com.codenicely.discountstore.project_new.state.OnStatesReceived;
import com.codenicely.discountstore.project_new.state.data.SelectedStateData;
import com.codenicely.discountstore.project_new.state.data.StateData;
import com.codenicely.discountstore.project_new.state.data.StateDetails;
import com.codenicely.discountstore.project_new.state.model.StateProvider;
import com.codenicely.discountstore.project_new.state.view.StateView;
import com.paytm.pgsdk.Log;

import java.util.List;

/**
 * Created by ujjwal on 9/6/17.
 */
public class StatePresenterImpl implements StatePresenter {

	StateView stateView;
	StateProvider stateProvider;

	public StatePresenterImpl(StateView stateView, StateProvider stateProvider) {
		this.stateView = stateView;
		this.stateProvider = stateProvider;
	}


	@Override
	public void requestState(String access_token) {

		stateView.showLoading(true);
		stateProvider.requestState(access_token, new OnStatesReceived() {
			@Override
			public void onFailure() {
				stateView.showLoading(false);
				stateView.showMessage("Please try again in some time");
			}

			@Override
			public void onSuccess(StateData stateData) {
				stateView.showLoading(false);
				stateView.onStateListRecieved(stateData.getStateDetailsList());
				Log.d("STATELIST----",""+stateData.getStateDetailsList());
			}
		});


	}
	@Override
	public void sendSelectedState(final String state_name, final int state_id, String access_token) {
		stateView.showLoading(true);
		stateView.onStateSelectSuccess(state_name,state_id);


/*
		stateProvider.sendSelectedState(state_id,access_token, new OnStateSent() {

			@Override
			public void onFailure() {
				stateView.showLoading(false);
				stateView.showMessage("Please try again in some time");
			}

			@Override
			public void onSuccess(SelectedStateData selectedStateData) {

				if (selectedStateData.isSuccess()) {
					stateView.showLoading(false);
					stateView.onStateSelectSuccess(state_name,state_id);
				} else {
					stateView.showLoading(false);
					stateView.showMessage(selectedStateData.getMessage());

				}
			}
		});
*/


	}

}
