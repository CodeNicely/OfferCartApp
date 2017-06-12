package com.codenicely.brandstore.project.state.view;

import com.codenicely.brandstore.project.state.data.StateDetails;

import java.util.List;

/**
 * Created by aman on 15/10/16.
 */
public interface StateView {
    void showLoading(boolean show);

    void showMessage(String message);

    void onStateListRecieved(List<StateDetails> stateDetailsList);

    void onStateSelected(int state_id, String state_name);

    void onStateSelectSuccess(String state_name,int state_id);
}

