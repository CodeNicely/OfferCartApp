package com.example.aman.offercart_v1.shops.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.home.HomePage;
import com.example.aman.offercart_v1.shops.model.data.ShopData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iket on 22/10/16.
 */
public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder> {
    private List<ShopData> shopDatas = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public ShopAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
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
        Picasso.with(context)
                .load(shopData.getImage())
                .into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof HomePage) {
                    ((HomePage) context).onShopSelected(shopData.getId(), shopData.getName());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopDatas.size();
    }


    protected class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, address;
        ImageView image;

        private MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.shop_name);
            address = (TextView) itemView.findViewById(R.id.shop_address);
            image = (ImageView) itemView.findViewById(R.id.shop_image);
        }

    }
}
