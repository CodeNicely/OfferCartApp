package com.example.aman.offercart_v1.offer.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.helper.SharedPrefs;
import com.example.aman.offercart_v1.offer.models.RetrofitOfferScreenDetailsProvider;
import com.example.aman.offercart_v1.offer.models.data.OfferScreenDetails;
import com.example.aman.offercart_v1.offer.presenter.OfferScreenDetailsPresenter;
import com.example.aman.offercart_v1.offer.presenter.OfferScreenDetailsPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.message;

/**
 * Created by aman on 19/10/16.
 */

public class OfferScreenActivity extends Activity implements OfferScreenView {

    @BindView(R.id.offersRecycler)
    RecyclerView recyclerView;
    @BindView(R.id.offersProgressBar)
    ProgressBar progressBar;
    private OfferScreenAdapter offerScreenAdapter;
    private OfferScreenDetailsPresenter offerScreenDetailsPresenter;
    private LinearLayoutManager linearLayoutManager;
    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityscreen);
        Log.d("Res","1");


        ButterKnife.bind(this);
        Log.d("Res","2");
        sharedPrefs=new SharedPrefs(this);
        offerScreenDetailsPresenter=new OfferScreenDetailsPresenterImpl
                (this,new RetrofitOfferScreenDetailsProvider());
        offerScreenAdapter=new OfferScreenAdapter(this);
        Log.d("Res","3");
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(offerScreenAdapter);
        offerScreenDetailsPresenter.requestOfferList();
    }

    @Override
    public void showMessage(String error) {
        Toast.makeText(OfferScreenActivity.this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void onOfferVerified(List<OfferScreenDetails> offerScreenDetailsList) {
        offerScreenAdapter.setdata(offerScreenDetailsList);
        offerScreenAdapter.notifyDataSetChanged();
        for(int i=0;i<offerScreenDetailsList.size();i++)

        {
            OfferScreenDetails offerScreenDetails= offerScreenDetailsList.get(i);

        }
    }

    @Override
    public void onOfferSelected(int offer_id, String offer_code, String offer_name) {
          /*  offerScreenDetailsPresenter=new OfferScreenDetailsPresenterImpl
                    (this,new RetrofitOfferScreenUpdateProvider());
            offerScreenDetailsPresenter.responseOfferList(offer_id,offer_code,offer_name);
 */   }

    @Override
    public void onOfferSent() {
            //Intent Work Left Here//
    }
}
