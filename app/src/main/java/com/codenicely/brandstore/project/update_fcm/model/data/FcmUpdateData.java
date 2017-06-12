package com.codenicely.brandstore.project.update_fcm.model.data;

/**
 * Created by ramya on 7/3/17.
 */

public class FcmUpdateData {
    private String message;
    private  boolean success;
    public FcmUpdateData(String message,boolean success)
    {
        this.message=message;
        this.success=success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
