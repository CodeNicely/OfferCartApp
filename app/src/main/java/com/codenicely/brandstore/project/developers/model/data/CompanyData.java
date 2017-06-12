package com.codenicely.brandstore.project.developers.model.data;

/**
 * Created by meghal on 17/10/16.
 */

public class CompanyData {

    private String company;
    private String address;
    private String email;
    private String facebook;
    private String contact;
    private String about;
    private String companyImage;
    private String website;

    public CompanyData(String company, String address, String email, String facebook, String contact, String about, String companyImage, String website) {
        this.company = company;
        this.address = address;
        this.email = email;
        this.facebook = facebook;
        this.contact = contact;
        this.about = about;
        this.companyImage = companyImage;
        this.website = website;
    }


    public String getWebsite() {
        return website;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getContact() {
        return contact;
    }

    public String getAbout() {
        return about;
    }

    public String getCompanyImage() {
        return companyImage;
    }
}
