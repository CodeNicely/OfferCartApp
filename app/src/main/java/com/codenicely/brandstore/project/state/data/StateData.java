package com.codenicely.brandstore.project.state.data;

import java.util.List;

/**
 * Created by ujjwal on 9/6/17.
 */
public class StateData {
	private boolean success;
	private String message;
	private List<StateDetails> state_data;

	public StateData(boolean success, String message, List<StateDetails> stateDetailsList) {
		this.success = success;
		this.message = message;
		this.state_data = stateDetailsList;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public List<StateDetails> getStateDetailsList() {
		return state_data;
	}
}
