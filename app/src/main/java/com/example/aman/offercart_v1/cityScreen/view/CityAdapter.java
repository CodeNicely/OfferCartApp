package com.example.aman.offercart_v1.cityScreen.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.cityScreen.models.data.CityScreenData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iket on 19/10/16.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {
    private List<CityScreenData> cityScreenDataList=new ArrayList<>();
    private Context context;
    private CityScreenView cityScreenView;
    private LayoutInflater layoutInflater;

    public CityAdapter(Context context) {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        cityScreenView=new CityScreenActivity();
    }
    public void setData(List<CityScreenData> cityScreenData)
    {
        cityScreenDataList=cityScreenData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.activity_cities_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final CityScreenData cityScreenData=cityScreenDataList.get(position);

        holder.city.setText(cityScreenData.getCity_name());

        holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityScreenView.onCitySelected(cityScreenData.getCity_id(),cityScreenData.getCity_name());

            }
        });

    }


    @Override
    public int getItemCount() {
        return cityScreenDataList.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {

        TextView city;
        Button select;
        private Context context2;

        private MyViewHolder(View itemView) {
            super(itemView);
            context2=itemView.getContext();
            city= (TextView) itemView.findViewById(R.id.city);
            select=(Button)itemView.findViewById(R.id.select_button);

        }

    }
}
