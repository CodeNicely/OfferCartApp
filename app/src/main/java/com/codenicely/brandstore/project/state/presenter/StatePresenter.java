package com.codenicely.brandstore.project.state.presenter;

/**
 * Created by ujjwal on 9/6/17.
 */
public interface StatePresenter {

	void sendSelectedState(String city,int state_id, String access_token) ;

	void requestState(String access_token) ;
}
