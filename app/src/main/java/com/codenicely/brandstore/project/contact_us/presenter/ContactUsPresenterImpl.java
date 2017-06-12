package com.codenicely.brandstore.project.contact_us.presenter;


import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.contact_us.ContactUsCallback;
import com.codenicely.brandstore.project.contact_us.model.ContactUsProvider;
import com.codenicely.brandstore.project.contact_us.model.data.ContactUsData;
import com.codenicely.brandstore.project.contact_us.view.ContactUsView;
import com.codenicely.brandstore.project.helper.MyApplication;

/**
 * Created by meghal on 15/10/16.
 */

public class ContactUsPresenterImpl implements ContactUsPresenter {

    private ContactUsView contactUsView;
    private ContactUsProvider contactUsProvider;

    public ContactUsPresenterImpl(ContactUsView contactUsView, ContactUsProvider contactUsProvider) {
        this.contactUsView = contactUsView;
        this.contactUsProvider = contactUsProvider;
    }

    @Override
    public void requestContactUs() {

        contactUsView.showLoader(true);
        contactUsProvider.requestContactUs(new ContactUsCallback() {
            @Override
            public void onSuccess(ContactUsData contactUsData) {

                contactUsView.showLoader(false);
                if (contactUsData.isSuccess()) {
                    contactUsView.setData(contactUsData);
//                    contactUsView.showMessage(contactUsData.getMessage());
                } else {
                    contactUsView.showMessage(contactUsData.getMessage());
                }
            }

            @Override
            public void onFailure() {

                contactUsView.showLoader(false);
                contactUsView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));
            }
        });

    }

    @Override
    public void onDestroy() {
        contactUsProvider.onDestroy();
    }
}
