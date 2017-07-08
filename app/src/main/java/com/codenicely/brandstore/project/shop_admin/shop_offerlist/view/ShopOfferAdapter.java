package com.codenicely.brandstore.project.shop_admin.shop_offerlist.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.helper.SharedPrefs;
import com.codenicely.brandstore.project.helper.image_loader.GlideImageLoader;
import com.codenicely.brandstore.project.helper.image_loader.ImageLoader;
import com.codenicely.brandstore.project.shop_admin.shop_edit_offer.view.OfferEditFragment;
import com.codenicely.brandstore.project.shop_admin.shop_home.ShopHomePage;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.model.RetrofitShopOfferListProvider;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.model.data.ShopOfferListDetails;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.presenter.ShopOfferListPresenter;
import com.codenicely.brandstore.project.shop_admin.shop_offerlist.presenter.ShopOfferListPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 16/5/17.
 */

public class ShopOfferAdapter extends RecyclerView.Adapter<ShopOfferAdapter.MyViewHolder> {


    private SharedPrefs sharedPrefs;
    private Context context;
    private LayoutInflater layoutInflater;
    private List<ShopOfferListDetails> shopOfferListDetailses = new ArrayList<>();
    private ImageLoader imageLoader;
    private ShopOfferListPresenter shopOfferListPresenter;

    public ShopOfferAdapter(Context context, ShopOfferListFragment shopOfferListFragment) {
        this.context = context;

        this.layoutInflater = LayoutInflater.from(context);
        imageLoader = new GlideImageLoader(context);
        shopOfferListPresenter = new ShopOfferListPresenterImpl(new RetrofitShopOfferListProvider(), shopOfferListFragment);
        //  shopOfferListPresenter = new ShopOfferListPresenterImpl(new MockShopOfferListProvider(), shopOfferListFragment);
    }

    @Override
    public ShopOfferAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		sharedPrefs=new SharedPrefs(context);
        View view = layoutInflater.inflate(R.layout.shop_offer_item, parent, false);
        return new ShopOfferAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopOfferAdapter.MyViewHolder holder, int position) {
        final ShopOfferListDetails shopOfferListDetails = shopOfferListDetailses.get(position);
        holder.title.setText(shopOfferListDetails.getOffer_title());
        holder.validity1.setText("Offer is valid till ");
        holder.validity1.append(String.valueOf(shopOfferListDetails.getValidity()));
        holder.validity1.append(" more days");

        holder.validity2.setText("This offer is valid till ");
        holder.validity2.append(shopOfferListDetails.getExpiry_date());


        holder.description.setText(shopOfferListDetails.getOffer_description());

        holder.edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //call edit fragment
                String offer_id_string = String.valueOf(shopOfferListDetails.getOffer_id());
                String offer_name_string = shopOfferListDetails.getOffer_title();
                String offer_description_string = shopOfferListDetails.getOffer_description();
                String offer_image_string = shopOfferListDetails.getOffer_image();
                String expiry_date=shopOfferListDetails.getExpiry_date();
              //  String year =shopOfferListDetails.get


                Fragment fragment = new OfferEditFragment();
                FragmentManager fm = ((ShopHomePage) context).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Bundle args = new Bundle();

				args.putString("offer_id", offer_id_string);
                args.putString("offer_name", offer_name_string);
                args.putString("offer_description", offer_description_string);
                args.putString("offer_image", offer_image_string);
                args.putString("offer_expiry_date", expiry_date);
				Log.d("Name --------------",offer_name_string);

				fragment.setArguments(args);
                ft.replace(R.id.home_layout, fragment);
                ft.addToBackStack(null);
                ft.commit();
                ((ShopHomePage) context).getSupportActionBar().hide();

            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog ad = new AlertDialog.Builder(context,R.style.YourDialogStyle)
                                               .create();
                ad.setCancelable(false);
                ad.setTitle("Delete ?");
                ad.setMessage("Do you really want to delete this offer ?");
                ad.setButton(DialogInterface.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shopOfferListPresenter.delete(sharedPrefs.getKeyAccessTokenShop(), shopOfferListDetails.getOffer_id());
                        ad.cancel();
                    }
                });
                ad.setButton(DialogInterface.BUTTON_NEGATIVE, "no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ad.cancel();
                    }
                });
                ad.show();

            }
        });

        imageLoader.loadImage(shopOfferListDetails.getOffer_image(), holder.image, holder.imageProgressBar);

    }

    public void setData(List<ShopOfferListDetails> shopOfferListDetailses) {
        this.shopOfferListDetailses = shopOfferListDetailses;
    }


    @Override
    public int getItemCount() {
        return shopOfferListDetailses.size();
    }


    protected class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title, cost, validity1, validity2, description;
        private ImageView image;
        private Button edit, delete;
        private ProgressBar imageProgressBar;


        private MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.offer_title);
            description = (TextView) itemView.findViewById(R.id.offer_description);
            validity1 = (TextView) itemView.findViewById(R.id.offer_validity);
            //  cost = (TextView) itemView.findViewById(R.id.offer_cost);
            image = (ImageView) itemView.findViewById(R.id.image);
            validity2 = (TextView) itemView.findViewById(R.id.offer_expiry);
            imageProgressBar = (ProgressBar) itemView.findViewById(R.id.imageProgressBar);
            edit = (Button) itemView.findViewById(R.id.edit);
            delete = (Button) itemView.findViewById(R.id.delete);
        }
    }
}