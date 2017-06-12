package com.codenicely.brandstore.project.state;

import com.codenicely.brandstore.project.state.data.StateData;

/**
 * Created by ujjwal on 9/6/17.
 */
public interface OnStatesReceived {

	void onFailure();

	void onSuccess(StateData stateData);
}
