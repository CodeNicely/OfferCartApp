package com.codenicely.brandstore.project.city.view;

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

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.categories.view.CategoryFragment;
import com.codenicely.brandstore.project.city.models.RetrofitCityProvider;
import com.codenicely.brandstore.project.city.data.CityDetails;
import com.codenicely.brandstore.project.city.presenter.CityPresenter;
import com.codenicely.brandstore.project.city.presenter.CityPresenterImpl;
import com.codenicely.brandstore.project.helper.SharedPrefs;
import com.codenicely.brandstore.project.home.HomePage;

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
public class CityFragment extends Fragment implements CityView {
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
    private CityPresenter cityPresenter;
    private SharedPrefs sharedPrefs;
    private String access_token;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
	int state_id;
    private OnFragmentInteractionListener mListener;
    private Context context;
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
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /*state_id = getArguments().getInt("state_id");
        */
		View view = inflater.inflate(R.layout.fragment_city, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        ((HomePage) getActivity()).getSupportActionBar().hide();
        toolbar.setTitle("Select City");

        sharedPrefs = new SharedPrefs(getContext());
       /* AdView adView = (AdView)view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);*/

		state_id=sharedPrefs.getStateId();

        if (sharedPrefs.getCity().equals("NA")) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getContext(), "Please Select City", Toast.LENGTH_SHORT).show();

                }
            });
        }
		else {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
        }

        access_token = sharedPrefs.getAccessToken();

        cityPresenter = new CityPresenterImpl(this, new RetrofitCityProvider());
        cityAdapter = new CityAdapter(getContext(), this);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cityAdapter);
        cityPresenter.requestCity(access_token,state_id);


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
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCityListRecieved(List<CityDetails> cityDetailsList) {
        cityAdapter.setData(cityDetailsList);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCitySelected(int city_id, String city_name) {
        sharedPrefs = new SharedPrefs(context);
        access_token = sharedPrefs.getAccessToken();

        cityPresenter.sendSelectedCity(city_name, city_id, access_token);


    }

    @Override
    public void onCitySelectSuccess(String city) {
        sharedPrefs.setCity(city);
        ((HomePage) context).setFragment(new CategoryFragment(), "Home");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
