package com.codenicely.brandstore.project.update_fcm;

import android.util.Log;

import com.codenicely.brandstore.project.helper.SharedPrefs;
import com.codenicely.brandstore.project.update_fcm.model.RetrofitFcmUpdater;
import com.codenicely.brandstore.project.update_fcm.presenter.FcmTokenUpdater;
import com.codenicely.brandstore.project.update_fcm.presenter.FcmTokenUpdaterImpl;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by ramya on 8/3/17.
 */

public class FirebaseFcmUpdateService extends FirebaseInstanceIdService implements FcmUpdaterView {
    private FcmTokenUpdater fcmTokenUpdater;
    private static final String TAG = "Firebase Instance";
    private SharedPrefs sharedPrefs = new SharedPrefs(this);

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.

        sendFcm(sharedPrefs.getAccessToken(),refreshedToken);
    }

    @Override
    public void sendFcm(String access_token, String fcm) {
        fcmTokenUpdater = new FcmTokenUpdaterImpl(this, new RetrofitFcmUpdater());

        if (fcm != null) {
            fcmTokenUpdater.sendFcmTokenUpdate(access_token, fcm);
        }

    }
}
