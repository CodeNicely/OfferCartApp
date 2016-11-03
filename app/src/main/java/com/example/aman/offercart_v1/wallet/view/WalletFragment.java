package com.example.aman.offercart_v1.wallet.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aman.offercart_v1.R;
import com.example.aman.offercart_v1.home.HomePage;
import com.example.aman.offercart_v1.home.HomePageInterface;
import com.example.aman.offercart_v1.payement.view.PaymentActivity;
import com.example.aman.offercart_v1.wallet.model.RetrofitWalletProvider;
import com.example.aman.offercart_v1.wallet.model.data.WalletData;
import com.example.aman.offercart_v1.wallet.presenter.WalletPresenter;
import com.example.aman.offercart_v1.wallet.presenter.WalletPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WalletFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WalletFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WalletFragment extends Fragment implements WalletInterface{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PAGE = "ARG_PAGE";


    @BindView(R.id.proceed)
    Button proceed;

    @BindView(R.id.wallet_toolbar)
    Toolbar toolbar;

    @BindView(R.id.add_50)
    Button add50;

    @BindView(R.id.add_100)
    Button add100;

    @BindView(R.id.add_200)
    Button add200;

    @BindView(R.id.wallet_amount)
    EditText amount;

    @BindView(R.id.wallet_balance)
    TextView balance;

    @BindView(R.id.wallet_progressbar)
    ProgressBar progressBar;


    // TODO: Rename and change types of parameters
    private static int mPage;


    private OnFragmentInteractionListener mListener;
    private WalletPresenter walletPresenter;

    public WalletFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param page Parameter 1.
     * @return A new instance of fragment WalletFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WalletFragment newInstance(int page) {

        mPage = page;
        WalletFragment fragment = new WalletFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.tab_add_money, container, false);
        walletPresenter=new WalletPresenterImpl(this,new RetrofitWalletProvider());
        ButterKnife.bind(this, view);

        toolbar.setNavigationIcon(ContextCompat.getDrawable(getContext(),R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        add50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("50");
            }
        });
        add100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("100");
            }
        });
        add200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("200");
            }
        });


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomePage)getActivity()).payement(amount.getText().toString());
            }
        });

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
    public void showMessage(String message) {
        Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressbar(boolean show) {
        if(show)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void walletReceived(WalletData walletData) {
        balance.setText(walletData.getBalance());
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
