package com.example.aman.offercart_v1.offerscreen.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.offerscreen.models.data.OfferScreenDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


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
        offerScreenView = new OfferScreenActivity();

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
        holder.offer.setText(offerScreenDetails.getOffer_name());
        holder.offer_des.setText(offerScreenDetails.getOffer_des());

        //holder.imageView.setImage(offerScreenDetails.getOffer_image());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                offerScreenView.onOfferSelected(offerScreenDetails.getOffer_id(),
                                                offerScreenDetails.getOffer_code(),
                                                offerScreenDetails.getOffer_name());



            }



        });

        holder.offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                offerScreenView.onOfferSelected(offerScreenDetails.getOffer_id(),
                        offerScreenDetails.getOffer_code(),
                        offerScreenDetails.getOffer_name());



            }



        });

        holder.offer_des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                offerScreenView.onOfferSelected(offerScreenDetails.getOffer_id(),
                        offerScreenDetails.getOffer_code(),
                        offerScreenDetails.getOffer_name());



            }



        });






    }

    @Override
    public int getItemCount() {
        return 0;
    }



    protected class MyViewHolder extends RecyclerView.ViewHolder {

        TextView offer,offer_des;
        ImageView imageView;

        private Context context2;

        private MyViewHolder(View itemView) {
            super(itemView);
            context2=itemView.getContext();
            offer= (TextView) itemView.findViewById(R.id.offer);
            offer_des= (TextView) itemView.findViewById(R.id.offer_des);
            imageView= (ImageView) itemView.findViewById(R.id.imageView);
            //final OfferScreenDetails offerScreenDetails =offerScreenDetailsList.get(imageView);
//            Picasso.with(context)
//                    .load(offerScreenDetails.getOffer_image())
//                    .error(R.drawable.happy)
//                    .into(imageView);
        }

    }
}