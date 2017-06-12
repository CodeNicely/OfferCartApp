package com.codenicely.brandstore.project.update_fcm.presenter;

import android.util.Log;

import com.codenicely.brandstore.project.update_fcm.FcmUpdateCallback;
import com.codenicely.brandstore.project.update_fcm.FcmUpdaterView;
import com.codenicely.brandstore.project.update_fcm.model.FcmUpdateHelper;
import com.codenicely.brandstore.project.update_fcm.model.data.FcmUpdateData;

/**
 * Created by ramya on 7/3/17.
 */

public class FcmTokenUpdaterImpl implements FcmTokenUpdater {
    private FcmUpdateHelper fcmUpdateHelper;
    private FcmUpdaterView fcmUpdaterView;
    public FcmTokenUpdaterImpl(FcmUpdaterView fcmUpdaterView,FcmUpdateHelper fcmUpdateHelper)
    {
        this.fcmUpdaterView=fcmUpdaterView;
        this.fcmUpdateHelper=fcmUpdateHelper;
    }

    @Override
    public void sendFcmTokenUpdate(String access_token, final String fcm) {
        fcmUpdateHelper.sendFcmToken(access_token, fcm, new FcmUpdateCallback() {
            @Override
            public void onSuccess(FcmUpdateData fcmUpdateData) {
                Log.d("messageRecieved",fcmUpdateData.getMessage());
            }
            @Override
            public void onFailure() {
                Log.d("message","something went wrong!");
            }
        });

    }
}
