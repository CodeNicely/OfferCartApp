package com.codenicely.brandstore.project.categories.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.categories.model.data.CategoryData;
import com.codenicely.brandstore.project.helper.image_loader.GlideImageLoader;
import com.codenicely.brandstore.project.helper.image_loader.ImageLoader;
import com.codenicely.brandstore.project.home.HomePage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iket on 19/10/16.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private List<CategoryData> categoryDatas = new ArrayList<>();
    private Context context;
    private CategoriesView categoriesView;
    private LayoutInflater layoutInflater;
    private CategoryFragment categoryFragment;
    private ImageLoader imageLoader;

    public CategoryAdapter(Context context, CategoryFragment categoryFragment) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        categoriesView = new CategoryFragment();
        this.categoryFragment = categoryFragment;
        imageLoader = new GlideImageLoader(context);
    }

    public void setData(List<CategoryData> cityScreenDataList) {
        categoryDatas = cityScreenDataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.category_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final CategoryData categoryData = categoryDatas.get(position);
        holder.name.setText(categoryData.getName());
        holder.description.setText(categoryData.getDescription());
        imageLoader.loadImage(categoryData.getImage(), holder.image, holder.imageProgressBar);

        holder.categoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof HomePage) {
                    ((HomePage) context).onCategorySelected(categoryData.getId(),categoryData.getName());
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return categoryDatas.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView image;
        private ProgressBar imageProgressBar;
        private LinearLayout categoryLayout;
        private TextView description;

        private MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.categoryName);
            image = (ImageView) itemView.findViewById(R.id.categoryView);
            categoryLayout = (LinearLayout) itemView.findViewById(R.id.categoryLayout);
            imageProgressBar = (ProgressBar) itemView.findViewById(R.id.imageProgressBar);
            description = (TextView) itemView.findViewById(R.id.categoryDescription);
        }

    }

}
