package com.codenicely.brandstore.project.shops.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.helper.image_loader.GlideImageLoader;
import com.codenicely.brandstore.project.helper.image_loader.ImageLoader;
import com.codenicely.brandstore.project.home.HomePage;
import com.codenicely.brandstore.project.shops.model.data.ShopData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iket on 22/10/16.
 */
public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder> {
    private List<ShopData> shopDatas = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;
    public ShopAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        imageLoader=new GlideImageLoader(context);
    }

    void setData(List<ShopData> data) {
        this.shopDatas = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.shop_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ShopData shopData = shopDatas.get(position);

        holder.name.setText(shopData.getName());
        holder.address.setText(shopData.getAddress());
		holder.distance.setText(String.format("%.3f",shopData.getDistance())+" km");
        imageLoader.loadImage(shopData.getImage(),holder.image,holder.imageProgressBar);
        holder.shopCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof HomePage) {
                    ((HomePage) context).onShopSelected(shopData.getShopId(), shopData.getName());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopDatas.size();
    }


    protected class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView shopCardView;
        private TextView name, address,distance;
        private ImageView image;
        private ProgressBar imageProgressBar;

        private MyViewHolder(View itemView) {
            super(itemView);

            shopCardView=(CardView)itemView.findViewById(R.id.shop_card);
            imageProgressBar=(ProgressBar)itemView.findViewById(R.id.imageProgressBar);
            name = (TextView) itemView.findViewById(R.id.shop_name);
            address = (TextView) itemView.findViewById(R.id.shop_address);
            image = (ImageView) itemView.findViewById(R.id.shop_image);
            distance= (TextView) itemView.findViewById(R.id.shop_distance);
        }
    }
}
