package com.codenicely.brandstore.project.city.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.city.data.CityDetails;
import com.codenicely.brandstore.project.home.HomePage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iket on 19/10/16.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {
    private List<CityDetails> cityDetailsList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private CityFragment cityFragment;

    public CityAdapter(Context context, CityFragment cityFragment) {
        this.context = context;
        this.cityFragment = cityFragment;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<CityDetails> cityDetails) {
        cityDetailsList = cityDetails;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.city_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final CityDetails cityDetails = cityDetailsList.get(position);

        holder.city.setText(cityDetails.getCity_name());


        holder.city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof HomePage) {
                    cityFragment.onCitySelected(cityDetails.getCity_id(), cityDetails.getCity_name());
                    holder.done.setVisibility(View.VISIBLE);
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return cityDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView city;
        private ImageView done;

        private MyViewHolder(View itemView) {
            super(itemView);

            done = (ImageView) itemView.findViewById(R.id.done);
            city = (TextView) itemView.findViewById(R.id.city);


        }

    }
}
