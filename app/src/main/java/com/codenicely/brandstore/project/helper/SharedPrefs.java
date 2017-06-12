package com.codenicely.brandstore.project.helper;

/**
 * Created by aman on 12/10/16.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


public class SharedPrefs {

    // Shared preferences file name
    private static final String PREF_NAME = "welcome";
    private static final String PREF_NAME_LOGIN = "Login";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
	private static final String KEY_IS_LOGGEDIN_AS_SHOP = "isLoggedInAsShop";

	private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_LOGIN_TYPE = "loginType";
    private static final String KEY_FCM = "fcm";
    private static final String KEY_ACCESS_TOKEN = "access_token";
	private static final String KEY_ACCESS_TOKEN_SHOP = "access_token_shop";

	private static final String KEY_CITY = "Raipur";
	private static final String KEY_STATE = "Chattisgarh";
	private static final String KEY_STATE_ID ="State Id";



	private static final int KEY_VERSION = 1;
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";



    // LogCat tag
    private static String TAG = "Shared Preference";

    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    public SharedPrefs(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static int getKeyVersion() {
        return KEY_VERSION;
    }

    public String getCity() {
        return pref.getString(KEY_CITY, "NA");
    }
	public int getStateId() {
		return pref.getInt(KEY_STATE_ID, 0);
	}

	public String getState() {
		return pref.getString(KEY_STATE, "NA");
	}
	public void setState(int state_id) {
		editor.putInt(KEY_STATE_ID, state_id);
		editor.commit();
	}

	public void setState(String state) {
		editor.putString(KEY_STATE, state);
		editor.commit();
	}
    public void setCity(String city) {
        editor.putString(KEY_CITY, city);
        editor.commit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        // commit changes
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }

	public void setShopLogin(boolean isLoggedIn) {

		editor.putBoolean(KEY_IS_LOGGEDIN_AS_SHOP, isLoggedIn);
		// commit changes
		editor.commit();
		Log.d(TAG, "User login session modified!");
	}


	public boolean isLoggedInasShop() {
		return pref.getBoolean(KEY_IS_LOGGEDIN_AS_SHOP, false);
	}

	public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getUsername() {
        return pref.getString(KEY_USERNAME, "Not Available");
    }

    public void setUsername(String username) {

        editor.putString(KEY_USERNAME, username);
        editor.commit();


    }


    public void setEmailId(String emailId) {

        editor.putString(KEY_EMAIL, emailId);
        editor.commit();

    }

    public String getUserId() {

        return pref.getString(KEY_USER_ID, "Not Available");

    }

    public void setUserId(String userId) {

        editor.putString(KEY_USER_ID, userId);
        editor.commit();

    }

    public String getAccessToken() {

        return pref.getString(KEY_ACCESS_TOKEN, null);
    }

    public void setAccessToken(String accessToken) {
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

	public String getKeyAccessTokenShop() {
		return pref.getString(KEY_ACCESS_TOKEN_SHOP, null);
	}

	public void setAccessTokenShop(String accessToken) {
		editor.putString(KEY_ACCESS_TOKEN_SHOP, accessToken);
		editor.commit();
	}


	public String getEmail() {

        return pref.getString(KEY_EMAIL, "Not Available");
    }

    public int getLoginType() {
        return pref.getInt(KEY_LOGIN_TYPE, 0);
    }

    public void setLoginType(int loginType) {
        editor.putInt(KEY_LOGIN_TYPE, loginType);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    //Welcome_Screen
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }


}