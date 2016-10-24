package com.example.aman.offercart_v1.categories.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.categories.model.data.CategoryData;
import com.example.aman.offercart_v1.home.HomePage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iket on 19/10/16.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{
    private List<CategoryData> categoryDatas=new ArrayList<>();
    private Context context;
    private CategoriesView categoriesView;
    private LayoutInflater layoutInflater;

    public CategoryAdapter(Context context) {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        categoriesView=new CategoryFragment();
    }
    public void setData(List<CategoryData> cityScreenDataList)
    {
        categoryDatas=cityScreenDataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.home_category_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

       final CategoryData categoryData=categoryDatas.get(position);
        holder.name.setText(categoryData.getName());
        Picasso.with(context)
                .load(categoryData.getImage())
                .into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(context instanceof HomePage)
                {
                    ( (HomePage)context).onCategorySelected(categoryData.getId());
                }
//                categoriesView.onSelected(categoryData.getId());

            }
        });

    }


    @Override
    public int getItemCount() {
        return categoryDatas.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;
        private Context context2;

        private MyViewHolder(View itemView) {
            super(itemView);
            context2=itemView.getContext();
            name=(TextView)itemView.findViewById(R.id.categoryName);
            image=(ImageView)itemView.findViewById(R.id.categoryView);
        }

    }

}
