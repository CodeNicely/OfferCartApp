package com.example.aman.offercart_v1.city.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.city.models.RetrofitCityScreenProvider;
import com.example.aman.offercart_v1.city.models.data.CityScreenData;
import com.example.aman.offercart_v1.city.presenter.CityScreenPresenter;
import com.example.aman.offercart_v1.city.presenter.CityScreenPresenterImpl;
import com.example.aman.offercart_v1.helper.SharedPrefs;
import com.example.aman.offercart_v1.home.HomePage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aman on 15/10/16.
 */
public class CityScreenActivity extends AppCompatActivity implements CityScreenView
{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.citiesRecycler)
    RecyclerView recyclerView;

    @BindView(R.id.citiesProgressbar)
    ProgressBar progressBar;

    private CityAdapter cityAdapter;
    private LinearLayoutManager linearLayoutManager;
    private CityScreenPresenter cityScreenPresenter;
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
        cityScreenPresenter=new CityScreenPresenterImpl(this,new RetrofitCityScreenProvider());
        cityAdapter=new CityAdapter(this);
        Log.d("Res","3");
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cityAdapter);
        cityScreenPresenter.requestCity();
    }


    @Override
    public void showLoading(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(CityScreenActivity.this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCityVerified(List<CityScreenData> cityScreenDataList) {

        cityAdapter.setData(cityScreenDataList);
        cityAdapter.notifyDataSetChanged();
        for(int i=0;i<cityScreenDataList.size();i++)
        {

            CityScreenData cityScreenData=cityScreenDataList.get(i);
            Log.d("response",cityScreenData.getCity_name());

        }

    }

    @Override
    public void onCitySelected(String city_id, String city_name) {
        Log.d("Response",city_name);
//        sharedPrefs=new SharedPrefs(this);
//        sharedPrefs.setKEY_City(city_name);
        cityScreenPresenter=new CityScreenPresenterImpl(this,new RetrofitCityScreenProvider());
        cityScreenPresenter.sendSelectedCity(city_id);

    }

    @Override
    public void onCitySent() {
        Intent in=new Intent(CityScreenActivity.this, HomePage.class);
        startActivity(in);
    }
}

