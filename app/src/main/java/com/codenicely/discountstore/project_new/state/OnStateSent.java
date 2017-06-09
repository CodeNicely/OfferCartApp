package com.codenicely.discountstore.project_new.state;

import com.codenicely.discountstore.project_new.state.data.SelectedStateData;

/**
 * Created by ujjwal on 9/6/17.
 */
public interface OnStateSent {
	void onFailure();

	void onSuccess(SelectedStateData selectedStateData);

}
