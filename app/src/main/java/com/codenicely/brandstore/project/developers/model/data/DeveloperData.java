package com.codenicely.brandstore.project.developers.model.data;

/**
 * Created by meghal on 17/10/16.
 */

public class DeveloperData {

    private boolean success;
    private String message;
    private CompanyData companyData;


    public DeveloperData(boolean success, String message, CompanyData companyData) {
        this.success = success;
        this.message = message;
        this.companyData = companyData;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public CompanyData getCompanyData() {
        return companyData;
    }
}
