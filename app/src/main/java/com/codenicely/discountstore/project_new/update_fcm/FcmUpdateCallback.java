package com.codenicely.discountstore.project_new.update_fcm;

import com.codenicely.discountstore.project_new.update_fcm.model.data.FcmUpdateData;

/**
 * Created by ramya on 7/3/17.
 */

public interface FcmUpdateCallback {
    void onSuccess(FcmUpdateData fcmUpdateData);
    void onFailure();
}
