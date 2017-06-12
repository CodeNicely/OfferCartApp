package com.codenicely.brandstore.project.state.model;

import com.codenicely.brandstore.project.state.OnStateSent;
import com.codenicely.brandstore.project.state.OnStatesReceived;

/**
 * Created by ujjwal on 9/6/17.
 */
public interface StateProvider {

	void requestState(String access_token, OnStatesReceived onStatesReceived);
	void sendSelectedState(int state_id, String access_token, OnStateSent onStateSent);

}
