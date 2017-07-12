package com.codenicely.brandstore.project.state.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.city.view.CityFragment;
import com.codenicely.brandstore.project.helper.SharedPrefs;
import com.codenicely.brandstore.project.home.HomePage;
import com.codenicely.brandstore.project.state.data.StateDetails;
import com.codenicely.brandstore.project.state.model.RetrofitStateProvider;
import com.codenicely.brandstore.project.state.presenter.StatePresenter;
import com.codenicely.brandstore.project.state.presenter.StatePresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StateFragment extends Fragment implements StateView {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;



	@BindView(R.id.stateRecyclerView)
	RecyclerView recyclerView;

	@BindView(R.id.stateProgressbar)
	ProgressBar progressBar;

	@BindView(R.id.toolbar)
	Toolbar toolbar;

	private StateAdapter stateAdapter;
	private LinearLayoutManager linearLayoutManager;
	private StatePresenter statePresenter;
	private SharedPrefs sharedPrefs;
	private String access_token;


	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment StateFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static StateFragment newInstance(String param1, String param2) {
		StateFragment fragment = new StateFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public StateFragment() {
		// Required empty public constructor
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

		View view = inflater.inflate(R.layout.fragment_state, container, false);
		ButterKnife.bind(this, view);

		((HomePage) getActivity()).getSupportActionBar().hide();
		toolbar.setTitle("Select State");

		sharedPrefs = new SharedPrefs(getContext());

		if (sharedPrefs.getCity().equals("NA")) {
			toolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					Toast.makeText(getContext(), "Please Select City", Toast.LENGTH_SHORT).show();

				}
			});
		} else {
			toolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					getActivity().onBackPressed();
				}
			});
		}
		access_token = sharedPrefs.getAccessToken();

		statePresenter = new StatePresenterImpl(this, new RetrofitStateProvider());
		stateAdapter = new StateAdapter(getContext(), this);

		linearLayoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(linearLayoutManager);
		recyclerView.setAdapter(stateAdapter);
		statePresenter.requestState(access_token);

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
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
											   + " must implement OnFragmentInteractionListener");
		}
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
	public void onStateListRecieved(List<StateDetails> stateDetailsList) {
		stateAdapter.setData(stateDetailsList);
		stateAdapter.notifyDataSetChanged();
	}

	@Override
	public void onStateSelected(int state_id, String state_name) {
		sharedPrefs = new SharedPrefs(getContext());
		access_token = sharedPrefs.getAccessToken();

		statePresenter.sendSelectedState(state_name,state_id, access_token);

	}

	@Override
	public void onStateSelectSuccess(String state_name,int state_id) {

		sharedPrefs.setState(state_name);
		sharedPrefs.setState(state_id);

		Fragment fragment = new CityFragment();
		FragmentManager fm = ((HomePage) getContext()).getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Bundle args = new Bundle();

		args.putInt("state_id",state_id);
		fragment.setArguments(args);
		ft.replace(R.id.home_layout, fragment);
//		ft.addToBackStack(null);
		ft.commit();
		((HomePage) getContext()).getSupportActionBar().hide();
		((HomePage) getContext()).setFragment(new CityFragment(), "Select City");

	}


	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p/>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}
}
