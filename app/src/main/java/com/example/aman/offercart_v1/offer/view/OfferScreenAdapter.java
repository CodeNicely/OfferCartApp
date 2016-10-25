package com.example.aman.offercart_v1.offer.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.offer.model.data.OfferScreenDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by aman on 21/10/16.
 */

public class OfferScreenAdapter extends RecyclerView.Adapter<OfferScreenAdapter.MyViewHolder> {

    private List<OfferScreenDetails> offerScreenDetailsList=new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private OfferScreenView offerScreenView;

    public OfferScreenAdapter(Context context) {
        this.context = context;
        layoutInflater = layoutInflater.from(context);
    }
    public void setdata(List<OfferScreenDetails> offerScreenDetails)
    {

        this.offerScreenDetailsList=offerScreenDetails;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.activity_offers_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final OfferScreenDetails offerScreenDetails =offerScreenDetailsList.get(position);
        holder.title.setText(offerScreenDetails.getOffer_name());
        holder.cost.setText(offerScreenDetails.getOffer_code());

                    Picasso.with(context)
                    .load(offerScreenDetails.getOffer_image())
                    .error(R.drawable.back1524)
                    .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return offerScreenDetailsList.size();
    }



    protected class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title,cost;
        ImageView image;

        private MyViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.offer_title);
            cost=(TextView)itemView.findViewById(R.id.offer_cost);
            image=(ImageView)itemView.findViewById(R.id.offer_image);

        }

    }
}
