package com.codenicely.discountstore.project_new.city.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.categories.view.CategoryFragment;
import com.codenicely.discountstore.project_new.city.models.RetrofitCityScreenProvider;
import com.codenicely.discountstore.project_new.city.models.data.CityScreenData;
import com.codenicely.discountstore.project_new.city.presenter.CityScreenPresenter;
import com.codenicely.discountstore.project_new.city.presenter.CityScreenPresenterImpl;
import com.codenicely.discountstore.project_new.helper.SharedPrefs;
import com.codenicely.discountstore.project_new.home.HomePage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CityFragment extends Fragment implements CityScreenView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    @BindView(R.id.citiesRecycler)
    RecyclerView recyclerView;

    @BindView(R.id.citiesProgressbar)
    ProgressBar progressBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private CityAdapter cityAdapter;
    private LinearLayoutManager linearLayoutManager;
    private CityScreenPresenter cityScreenPresenter;
    private SharedPrefs sharedPrefs;
    private String access_token;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CityFragment newInstance(String param1, String param2) {
        CityFragment fragment = new CityFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_city, container, false);
        ButterKnife.bind(this, view);

        ((HomePage)getActivity()).getSupportActionBar().hide();
        toolbar.setTitle("Select City");





        sharedPrefs = new SharedPrefs(getContext());

        if(sharedPrefs.getCity().equals("NA")){
           toolbar.setNavigationOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   Toast.makeText(getContext(), "Please Select City", Toast.LENGTH_SHORT).show();

               }
           });
        }else{
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
        }
        access_token = sharedPrefs.getAccessToken();

        cityScreenPresenter = new CityScreenPresenterImpl(this, new RetrofitCityScreenProvider());
        cityAdapter = new CityAdapter(getContext(), this);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cityAdapter);
        cityScreenPresenter.requestCity(access_token);


        return view;
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
    public void showLoading(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCityVerified(List<CityScreenData> cityScreenDataList) {
        cityAdapter.setData(cityScreenDataList);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCitySelected(int city_id, String city_name) {
        sharedPrefs = new SharedPrefs(getContext());
        access_token = sharedPrefs.getAccessToken();
        cityScreenPresenter.sendSelectedCity(city_name, city_id, access_token);


    }

    @Override
    public void onCitySent(String city) {
    /*    Intent in=new Intent(getActivity(), HomePage.class);
        startActivity(in);
        getActivity().finish();
    */
        sharedPrefs.setCity(city);
        ((HomePage) getContext()).setFragment(new CategoryFragment(), "Home");

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
