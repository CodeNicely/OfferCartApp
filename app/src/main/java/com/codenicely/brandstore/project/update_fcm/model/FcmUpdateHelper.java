package com.codenicely.brandstore.project.update_fcm.model;

import com.codenicely.brandstore.project.update_fcm.FcmUpdateCallback;

/**
 * Created by ramya on 7/3/17.
 */

public interface FcmUpdateHelper {
    void sendFcmToken(String access_token, String fcm, FcmUpdateCallback fcmUpdateCallback);

}
