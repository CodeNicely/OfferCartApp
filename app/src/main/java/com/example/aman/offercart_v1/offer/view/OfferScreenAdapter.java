package com.example.aman.offercart_v1.offer.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.offer.model.data.OfferScreenDetails;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by aman on 21/10/16.
 */

public class OfferScreenAdapter extends RecyclerView.Adapter<OfferScreenAdapter.MyViewHolder> {

    private List<OfferScreenDetails> offerScreenDetailsList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private ShopOfferFragment shopOfferFragment;

    public OfferScreenAdapter(Context context, ShopOfferFragment shopOfferFragment) {
        this.context = context;
        this.shopOfferFragment = shopOfferFragment;
        layoutInflater = layoutInflater.from(context);

    }

    public void setdata(List<OfferScreenDetails> offerScreenDetails) {

        this.offerScreenDetailsList = offerScreenDetails;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.activity_offers_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final OfferScreenDetails offerScreenDetails = offerScreenDetailsList.get(position);
        holder.title.setText(offerScreenDetails.getName());
        holder.cost.setText(String.valueOf(offerScreenDetails.getPrice()));
        holder.validity.setText(String.valueOf(offerScreenDetails.getValidity()));
        holder.description.setText(String.valueOf(offerScreenDetails.getDescription()));

        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shopOfferFragment.buyOffer(offerScreenDetails.getOffer_id(),offerScreenDetails.getPrice());

            }
        });
        Picasso.with(context)
                .load(offerScreenDetails.getImage())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return offerScreenDetailsList.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title, cost, validity, description;
        private ImageView image;
        private Button buy;
        private TextView shop_name;
        private TextView shop_address;
        private TextView shop_description;


        private MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.offer_title);
            description = (TextView) itemView.findViewById(R.id.offer_description);
            validity = (TextView) itemView.findViewById(R.id.offer_validity);
            cost = (TextView) itemView.findViewById(R.id.offer_cost);
            image = (ImageView) itemView.findViewById(R.id.image);
            buy = (Button) itemView.findViewById(R.id.buy);


        }

    }
}
