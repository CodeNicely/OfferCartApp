package com.example.aman.offercart_v1.city.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.city.models.data.CityScreenData;
import com.example.aman.offercart_v1.home.HomePage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iket on 19/10/16.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {
    private List<CityScreenData> cityScreenDataList=new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private CityFragment cityFragment;

    public CityAdapter(Context context,CityFragment cityFragment) {
        this.context=context;
        this.cityFragment=cityFragment;
        layoutInflater=LayoutInflater.from(context);
    }
    public void setData(List<CityScreenData> cityScreenData)
    {
        cityScreenDataList=cityScreenData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.city_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final CityScreenData cityScreenData=cityScreenDataList.get(position);

        holder.city.setText(cityScreenData.getCity_name());


        holder.city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if( context instanceof HomePage) {

                cityFragment.onCitySelected(cityScreenData.getCity_id(),cityScreenData.getCity_name());
                holder.done.setVisibility(View.VISIBLE);
            }

            }
        });

    }


    @Override
    public int getItemCount() {
        return cityScreenDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView city;
        private ImageView done;

        private MyViewHolder(View itemView) {
            super(itemView);

            done=(ImageView)itemView.findViewById(R.id.done);
            city= (TextView) itemView.findViewById(R.id.city);


        }

    }
}
