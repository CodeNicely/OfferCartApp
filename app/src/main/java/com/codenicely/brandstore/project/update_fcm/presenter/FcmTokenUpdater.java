package com.codenicely.brandstore.project.update_fcm.presenter;

/**
 * Created by ramya on 7/3/17.
 */

public interface FcmTokenUpdater {
    void sendFcmTokenUpdate(String access_token,String fcm);
}
