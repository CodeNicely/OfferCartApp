package com.codenicely.discountstore.project_new.shop_offerlist.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.categories.view.CategoryAdapter;
import com.codenicely.discountstore.project_new.helper.SharedPrefs;
import com.codenicely.discountstore.project_new.helper.image_loader.GlideImageLoader;
import com.codenicely.discountstore.project_new.helper.image_loader.ImageLoader;
import com.codenicely.discountstore.project_new.offer.model.data.OfferScreenDetails;
import com.codenicely.discountstore.project_new.shop_offerlist.model.MockShopOfferListProvider;
import com.codenicely.discountstore.project_new.shop_offerlist.model.RetrofitShopOfferListProvider;
import com.codenicely.discountstore.project_new.shop_offerlist.model.data.ShopOfferListData;
import com.codenicely.discountstore.project_new.shop_offerlist.model.data.ShopOfferListDetails;
import com.codenicely.discountstore.project_new.shop_offerlist.presenter.ShopOfferListPresenter;
import com.codenicely.discountstore.project_new.shop_offerlist.presenter.ShopOfferListPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 16/5/17.
 */

public class ShopOfferAdapter extends RecyclerView.Adapter<ShopOfferAdapter.MyViewHolder> {


private SharedPrefs sharedPrefs;
    private String access_token;
    private Context context;
    private LayoutInflater layoutInflater;
    private ShopOfferListFragment shopOfferListFragment;
    private ShopOfferListView shopOfferListView;
    private ShopOfferListDetails shopOfferListDetails;
    private List<ShopOfferListDetails> shopOfferListDetailses=new ArrayList<>();
    private ImageLoader imageLoader;
    private ShopOfferListPresenter shopOfferListPresenter;

    public ShopOfferAdapter(Context context, ShopOfferListFragment shopOfferListFragment) {
        this.context = context;

        this.layoutInflater = LayoutInflater.from(context);
        this.shopOfferListFragment = shopOfferListFragment;
        this.shopOfferListView = new ShopOfferListFragment();
        imageLoader=new GlideImageLoader(context);
      //  shopOfferListPresenter = new ShopOfferListPresenterImpl(new RetrofitShopOfferListProvider(), shopOfferListFragment);
        shopOfferListPresenter = new ShopOfferListPresenterImpl(new MockShopOfferListProvider(), shopOfferListFragment);
        }

    @Override
    public ShopOfferAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view=layoutInflater.inflate(R.layout.shop_offer_item,parent,false);
        return new ShopOfferAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ShopOfferAdapter.MyViewHolder holder, int position) {
        final ShopOfferListDetails shopOfferListDetails = shopOfferListDetailses.get(position);
        holder.title.setText(shopOfferListDetails.getOffer_title());
        holder.cost.setText("Rs. ");
        holder.cost.append(String.valueOf(shopOfferListDetails.getOffer_price()));
        holder.validity1.setText(String.valueOf(shopOfferListDetails.getOffer_validity_days()));
        holder.validity2.setText(String.valueOf(shopOfferListDetails.getOffer_validity_date()));
        holder.description.setText(String.valueOf(shopOfferListDetails.getOffer_description()));

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //call edit fragment

            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
     shopOfferListPresenter.delete(sharedPrefs.getKeyAccessTokenShop(),shopOfferListDetails.getOffer_id());
     //           shopOfferListPresenter.delete("dsdssd",shopOfferListDetails.getOffer_id());


            }
        });

        imageLoader.loadImage(shopOfferListDetails.getOffer_image(),holder.image,holder.imageProgressBar);


    }

    public void setData(List<ShopOfferListDetails> shopOfferListDetailses){
        this.shopOfferListDetailses=shopOfferListDetailses;
    }


    @Override
    public int getItemCount() {
     return shopOfferListDetailses.size();
    }


protected class MyViewHolder extends RecyclerView.ViewHolder {

    private TextView title, cost, validity1,validity2, description;
    private ImageView image;
    private Button edit,delete;
    private ProgressBar imageProgressBar;


    private MyViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.offer_title);
        description = (TextView) itemView.findViewById(R.id.offer_description);
        validity1 = (TextView) itemView.findViewById(R.id.offer_validity);
        cost = (TextView) itemView.findViewById(R.id.offer_cost);
        image = (ImageView) itemView.findViewById(R.id.image);
        validity2 = (TextView) itemView.findViewById(R.id.offer_validity2);
        imageProgressBar = (ProgressBar) itemView.findViewById(R.id.imageProgressBar);
        edit = (Button) itemView.findViewById(R.id.edit);
        delete = (Button) itemView.findViewById(R.id.delete);
    }
}
}