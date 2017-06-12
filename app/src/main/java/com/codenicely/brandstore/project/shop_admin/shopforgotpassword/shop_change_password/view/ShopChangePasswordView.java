package com.codenicely.brandstore.project.shop_admin.shopforgotpassword.shop_change_password.view;

/**
 * Created by ujjwal on 17/5/17.
 */
public interface ShopChangePasswordView {
	void showProgressbar(boolean show);
	void showMessage(String message);
	void onPasswordChanged();
}
