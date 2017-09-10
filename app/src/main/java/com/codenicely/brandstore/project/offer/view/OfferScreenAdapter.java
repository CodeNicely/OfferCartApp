package com.codenicely.brandstore.project.offer.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.helper.image_loader.GlideImageLoader;
import com.codenicely.brandstore.project.helper.image_loader.ImageLoader;
import com.codenicely.brandstore.project.offer.model.data.OfferScreenDetails;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by aman on 21/10/16.
 */

public class OfferScreenAdapter extends RecyclerView.Adapter<OfferScreenAdapter.MyViewHolder> {

    private List<OfferScreenDetails> offerScreenDetailsList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private OfferFragment offerFragment;
    private ImageLoader imageLoader;
    public OfferScreenAdapter(Context context, OfferFragment offerFragment) {
        this.context = context;
        this.offerFragment = offerFragment;
        layoutInflater = layoutInflater.from(context);
        imageLoader=new GlideImageLoader(context);
    }

    public void setdata(List<OfferScreenDetails> offerScreenDetails) {
        this.offerScreenDetailsList = offerScreenDetails;
    }

  @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.offers_item, parent, false);
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final OfferScreenDetails offerScreenDetails = offerScreenDetailsList.get(position);
        holder.title.setText(offerScreenDetails.getName());
  /*      holder.cost.setText("Rs. ");
        holder.cost.append(String.valueOf(offerScreenDetails.getPrice()));
  */
        holder.validity.setText("This offer is valid till ");
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy");
        holder.validity.append(""+String.valueOf(offerScreenDetails.getExpiry_date().getDate())+"/"
		+String.valueOf(offerScreenDetails.getExpiry_date().getMonth())+"/"+
		String.valueOf(sdf.format(offerScreenDetails.getExpiry_date())));
        holder.description.setText(String.valueOf(offerScreenDetails.getDescription()));

        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                offerFragment.getOffer(offerScreenDetails.getOffer_id(),offerScreenDetails.getPrice());

            }
        });

        imageLoader.loadImage(offerScreenDetails.getImage(),holder.image,holder.imageProgressBar);

    }

    @Override
    public int getItemCount() {
        return offerScreenDetailsList.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title, validity, description;
        private ImageView image;
        private Button buy;
        private ProgressBar imageProgressBar;

        private MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.offer_title);
            description = (TextView) itemView.findViewById(R.id.offer_description);
            validity = (TextView) itemView.findViewById(R.id.offer_validity);
            image = (ImageView) itemView.findViewById(R.id.image);
            buy = (Button) itemView.findViewById(R.id.buy);
            imageProgressBar=(ProgressBar)itemView.findViewById(R.id.imageProgressBar);

        }

    }
}
