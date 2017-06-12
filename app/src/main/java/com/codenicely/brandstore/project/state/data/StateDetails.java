package com.codenicely.brandstore.project.state.data;

/**
 * Created by ujjwal on 9/6/17.
 */
public class StateDetails {
	private int state_id;
	private String state_name;

	public StateDetails(int state_id, String state_name) {
		this.state_id = state_id;
		this.state_name = state_name;
	}

	public int getState_id() {
		return state_id;
	}

	public String getState_name() {
		return state_name;
	}
}
