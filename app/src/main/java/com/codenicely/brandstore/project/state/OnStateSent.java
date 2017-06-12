package com.codenicely.brandstore.project.state;

import com.codenicely.brandstore.project.state.data.SelectedStateData;

/**
 * Created by ujjwal on 9/6/17.
 */
public interface OnStateSent {
	void onFailure();

	void onSuccess(SelectedStateData selectedStateData);

}
