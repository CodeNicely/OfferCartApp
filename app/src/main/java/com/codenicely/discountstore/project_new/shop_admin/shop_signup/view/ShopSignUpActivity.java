package com.codenicely.discountstore.project_new.shop_admin.shop_signup.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.shops.model.data.ShopData;
import com.codenicely.discountstore.project_new.shops.view.ShopView;

import java.util.List;

public class ShopSignUpActivity extends AppCompatActivity implements ShopView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_sign_up);
    }

    @Override
    public void showLoading(boolean show) {
        if(show){

        }
        else {

        }

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnShopsDataReceived(List<ShopData> shopDataList) {

    }
}
