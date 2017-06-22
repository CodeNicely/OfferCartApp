package com.codenicely.brandstore.project.welcome_screen.view;

/**
 * Created by aman on 12/10/16.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.login.view.LoginScreenActivity;
import com.codenicely.brandstore.project.shop_admin.shop_activity.ShopActivity;
import com.codenicely.brandstore.project.welcome_screen.models.RetrofitWelcomeScreenProvider;
import com.codenicely.brandstore.project.welcome_screen.models.data.WelcomeImageDetails;
import com.codenicely.brandstore.project.welcome_screen.presenter.WelcomeScreenPresenter;
import com.codenicely.brandstore.project.welcome_screen.presenter.WelcomeScreenPresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeScreenActivity extends Activity implements WelcomeScreenView {

    private ViewPager viewPager;
    private ProgressBar progressBar;
    private ViewPagerAdapter viewPagerAdapter;
    private WelcomeScreenPresenter welcomeScreenPresenter;
    private TabLayout tabLayout;
    List<WelcomeImageDetails> welcomeImageDetails = new ArrayList<>();
    @BindView(R.id.button_login_shop)
    Button button_login_shop;

    @BindView(R.id.button_login_customer)
    Button button_login_customer;
    public static
    WelcomeScreenActivity welcomeScreenActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        welcomeScreenActivity=this;
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        initialise();
    }

    public void initialise() {
        final Context context = this;

        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        welcomeScreenPresenter = new WelcomeScreenPresenterImpl(this, new RetrofitWelcomeScreenProvider());
        welcomeScreenPresenter.getWelcomeData();
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        button_login_customer.setVisibility(View.GONE);
//        button_login_customer.setAlpha(0);
//        button_login_shop.setAlpha(0);
        button_login_shop.setVisibility(View.GONE);
        button_login_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(WelcomeScreenActivity.this, LoginScreenActivity.class);
                startActivity(i);

            }
        });

        button_login_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeScreenActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==welcomeImageDetails.size()-1){
//                    Animation startAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in_animation);
//                    button_login_customer.startAnimation(startAnimation);
//                    button_login_shop.startAnimation(startAnimation);

                    button_login_customer.setVisibility(View.VISIBLE);
                    button_login_shop.setVisibility(View.VISIBLE);
                }else {
                    button_login_customer.setVisibility(View.GONE);
                    button_login_shop.setVisibility(View.GONE);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    public void onShopButtonClick() {
        Intent intent = new Intent(WelcomeScreenActivity.this, ShopActivity.class);
        startActivity(intent);

    }

    void onCustomerButtonClick(){
        Intent i = new Intent(WelcomeScreenActivity.this, LoginScreenActivity.class);
        startActivity(i);

    }

    @Override
    public void showMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();

    }


    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setData(List<WelcomeImageDetails> welcomeImageDetails) {
        viewPagerAdapter.setImageList(welcomeImageDetails);
        this.welcomeImageDetails = welcomeImageDetails;
        viewPagerAdapter.notifyDataSetChanged();

     /*   pageSwitcher(3);
    */}

    Timer timer;
    int page = 1;

    public void pageSwitcher(int seconds) {
        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000); // delay
        // in
        // milliseconds
    }


    // this is an inner class...
    class RemindTask extends TimerTask {

        @Override
        public void run() {

            // As the TimerTask run on a seprate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.
            runOnUiThread(new Runnable() {
                public void run() {

                    if (page > 3) { // In my case the number of pages are 5
                        timer.cancel();
                        // Showing a toast for just testing purpose
/*
                        Toast.makeText(getApplicationContext(), "Timer stoped",
                                Toast.LENGTH_LONG).show();
*/
                    } else {
                        viewPager.setCurrentItem(page++);
                    }
                }
            });

        }
    }

}







/*    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

//        String s=getIntent().getExtras().getString("mobile");
//        Log.d("dd",s);

//         Checking for first time launch - before calling setContentView()
        prefManager = new SharedPrefs(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        setContentView(R.layout.activity_welcome);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btn_login = (Button) findViewById(R.id.btn_login);
        layouts = new int[]{
                R.layout.welcome_slide1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4};
        // adding bottom dots
        addBottomDots(0);

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);



        btn_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                    launchHomeScreen();

            }
        });
    }

    private void addBottomDots(int currentPage)
    {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        //prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeScreenActivity.this, LoginScreenActivity.class));

        finish();
    }





    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener()
    {

        @Override
        public void onPageSelected(int position)
        {
            addBottomDots(position);


        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2)
        {

        }

        @Override
        public void onPageScrollStateChanged(int arg0)
        {

        }
    };

    @Override
    public void showMessage(String message) {
        Toast.makeText(WelcomeScreenActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(List<WelcomeImageDetails> welcomeImageDetails)
    {

    }


    /*
     * View pager adapter
     */

/*
    public class MyViewPagerAdapter extends PagerAdapter
    {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
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

    private void initialize() {
        prefManager = new SharedPrefs(this);
        welcomeScreenPresenter = new WelcomeScreenPresenterImpl(this, new RetrofitWelcomeScreenProvider());
//        homeDetailsAdapter = new WelcomeImageDetailsAdapter(this);


    }

*/

