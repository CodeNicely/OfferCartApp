package com.example.aman.offercart_v1.helper;

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
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHOTO_URL = "photoUrl";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_LOGIN_TYPE = "loginType";
    private static final String KEY_FCM = "fcm";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_City = "raipur";
    private static final int KEY_VERSION=1;
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    // LogCat tag
    private static String TAG = "Shared Preference";

    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    public SharedPrefs(Context context)
    {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setKEY_City(String city)
    {
        editor.putString(KEY_City,city);
        editor.commit();
    }
    public String getKEY_City()
    {
        return pref.getString(KEY_City,"Raipur");
    }


    public static int getKeyVersion()
    {
        return KEY_VERSION;
    }

    public void setLogin(boolean isLoggedIn)
    {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        // commit changes
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn()
    {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public void setUsername(String username) {

        editor.putString(KEY_USERNAME, username);
        editor.commit();


    }

    public String getUsername()
    {
        return pref.getString(KEY_USERNAME, "Not Available");
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

    public String getPhotoUrl() {

        return pref.getString(KEY_PHOTO_URL, "Not Available");
    }

    public void setPhotoUrl(String photoUrl) {

        editor.putString(KEY_PHOTO_URL, photoUrl);
        editor.commit();

    }

    public void setAccessToken(String accessToken) {
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public String getAccessToken() {

        return pref.getString(KEY_ACCESS_TOKEN, null);
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

    //Welcome_Screen
    public void setFirstTimeLaunch(boolean isFirstTime)
    {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch()
    {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }



}