package com.codenicely.discountstore.project_new.wallet.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by aman on 24/10/16.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int TAB_COUNT = 1;
    private String tabTitles[] = new String[]{"Add"};
    //private Context context;


    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);

    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("Resp", "" + position + 1);
        return WalletFragment.newInstance(position + 1);

    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];
    }
}
