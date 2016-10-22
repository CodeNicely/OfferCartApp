package com.example.aman.offercart_v1.welcome_screen.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.welcome_screen.models.data.WelcomeImageDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 19/10/16.
 */

public class ViewPagerAdapter extends PagerAdapter
{
    private Context context;
    private List<WelcomeImageDetails> welcomeImageDetailsList = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    public void setImageList(List<WelcomeImageDetails> welcomeImageDetailsList)
    {
        this.welcomeImageDetailsList=welcomeImageDetailsList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.view_pager_item, container, false);
        container.addView(view);
        WelcomeImageDetails welcomeImageDetails = welcomeImageDetailsList.get(position);
        TextView textView = (TextView) view.findViewById(R.id.tv1);
        ImageView imageView=(ImageView)view.findViewById(R.id.img);
        textView.setText(welcomeImageDetails.getMessage());
        Picasso.with(context)
                .load(welcomeImageDetails.getImage_url())
                .error(R.drawable.happy)
                .into(imageView);

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
