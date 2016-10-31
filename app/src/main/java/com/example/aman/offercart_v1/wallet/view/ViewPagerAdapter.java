package com.example.aman.offercart_v1.wallet.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by aman on 24/10/16.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int TAB_COUNT= 2;
    private String tabTitles[] = new String[] { "", "Add"};
    //private Context context;


    public ViewPagerAdapter(FragmentManager manager)
    {
        super(manager);

    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return WalletFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position)
    {// Generate title based on item position

        return tabTitles[position];
    }
}
