package com.codenicely.brandstore.project.welcome_screen.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.helper.image_loader.GlideImageLoader;
import com.codenicely.brandstore.project.helper.image_loader.ImageLoader;
import com.codenicely.brandstore.project.welcome_screen.models.data.WelcomeImageDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 19/10/16.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<WelcomeImageDetails> welcomeImageDetailsList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;

    public ViewPagerAdapter(Context context) {

        imageLoader=new GlideImageLoader(context);
        this.context = context;
    }

    public void setImageList(List<WelcomeImageDetails> welcomeImageDetailsList) {
        this.welcomeImageDetailsList = welcomeImageDetailsList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.welcome_view_pager_item, container, false);
        container.addView(view);
        WelcomeImageDetails welcomeImageDetails = welcomeImageDetailsList.get(position);
        TextView textView = (TextView) view.findViewById(R.id.tv1);
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        ProgressBar imageProgressBar = (ProgressBar) view.findViewById(R.id.imageProgressBar);

        textView.setText(welcomeImageDetails.getMessage());
//        imageLoader.loadImage(welcomeImageDetails.getImage_url(),imageView,imageProgressBar);


        return view;
    }

    @Override
    public int getCount() {
        return welcomeImageDetailsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
