package com.codenicely.discountstore.project_new.shop_login.view;

/**
 * Created by ujjwal on 15/5/17.
 */
public interface ShopLoginView {
	void showProgressbar(boolean show);
	void showMessage(String message);
	void onLoginVerified();
}