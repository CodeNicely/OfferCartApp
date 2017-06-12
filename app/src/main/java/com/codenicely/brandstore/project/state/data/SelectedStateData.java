package com.codenicely.brandstore.project.state.data;

/**
 * Created by ujjwal on 9/6/17.
 */
public class SelectedStateData {
	private String message;
	private boolean success;

	public SelectedStateData(String message, boolean success) {
		this.message = message;
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public boolean isSuccess() {
		return success;
	}
}
