package com.codenicely.discountstore.project_new.categories.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.categories.model.RetrofitCategoriesProvider;
import com.codenicely.discountstore.project_new.categories.model.data.CategoryData;
import com.codenicely.discountstore.project_new.categories.presenter.CategoriesPresenter;
import com.codenicely.discountstore.project_new.categories.presenter.CategoriesPresenterImpl;
import com.codenicely.discountstore.project_new.helper.SharedPrefs;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment implements CategoriesView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    /* @BindView(R.id.tabLayout)
     TabLayout tabLayout;
 */
    @BindView(R.id.category_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.categories_progressbar)
    ProgressBar progressBar;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CategoriesPresenter categoriesPresenter;
    private CategoryAdapter categoryAdapter;
    private GridLayoutManager gridLayoutManager;
    private String token;
    private SharedPrefs sharedPrefs;
    private OnFragmentInteractionListener mListener;
    private InterstitialAd mInterstitialAd;

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ((AppCompatActivity) getActivity()).getSupportActionBar().show();


        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        AdView adView = (AdView)view.findViewById(R.id.adView);
        final AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(view.getContext());
        mInterstitialAd.setAdUnitId(getString(R.string.banner_splash_sub_screen));
        mInterstitialAd.loadAd(adRequest);
        initialize();
        categoriesPresenter.getCategories(token);
        //sharedPrefs.clearVisitCount();
        sharedPrefs.setVisitCount();
        Log.d("count",String.valueOf(sharedPrefs.getVisitCount()));
        
        if(sharedPrefs.getVisitCount()%7==0)
        {
            Toast.makeText(getContext(),"working",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getContext(),"incrementing",Toast.LENGTH_LONG).show();
        }
        Log.d("count",String.valueOf(sharedPrefs.getVisitCount()));
        getActivity().setTitle("Categories");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                display();
            }

        });
        return view;

    }
    void display()
    {
        if(mInterstitialAd.isLoaded())
        {
            mInterstitialAd.show();
        }
    }


    void initialize() {
        categoriesPresenter = new CategoriesPresenterImpl(this, new RetrofitCategoriesProvider());
        //        categoriesPresenter=new CategoriesPresenterImpl(this,new MockCategoryProvider());
        categoryAdapter = new CategoryAdapter(getContext(), this);
        //       recyclerView.setHasFixedSize(true);
        sharedPrefs = new SharedPrefs(getContext());
        token = sharedPrefs.getAccessToken();
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(categoryAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressbar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void onDataReceived(List<CategoryData> categoryDatas) {
        categoryAdapter.setData(categoryDatas);
        categoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void onSelected(String category_id) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
