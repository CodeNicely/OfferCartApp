package com.codenicely.discountstore.project_new.state;

import com.codenicely.discountstore.project_new.state.data.StateData;

/**
 * Created by ujjwal on 9/6/17.
 */
public interface OnStatesReceived {

	void onFailure();

	void onSuccess(StateData stateData);
}
