package com.codenicely.brandstore.project.contact_us.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codenicely.brandstore.project.R;
import com.codenicely.brandstore.project.contact_us.model.RetrofitContactUsProvider;
import com.codenicely.brandstore.project.contact_us.model.data.ContactUsData;
import com.codenicely.brandstore.project.contact_us.presenter.ContactUsPresenter;
import com.codenicely.brandstore.project.contact_us.presenter.ContactUsPresenterImpl;
import com.codenicely.brandstore.project.helper.image_loader.GlideImageLoader;
import com.codenicely.brandstore.project.helper.image_loader.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactUsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactUsFragment extends Fragment implements ContactUsView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.facebook)
    TextView facebook;
    @BindView(R.id.imageProgressBar)
    ProgressBar imageProgressBar;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.contactUsLayout)
    LinearLayout contactUsLayout;
    @BindView(R.id.emailCard)
    CardView emailCard;
    @BindView(R.id.phoneCard)
    CardView phoneCard;
    @BindView(R.id.locationCard)
    CardView locationCard;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageLoader imageLoader;
    private View snackView;
    private ContactUsPresenter contactUsPresenter;
    private OnFragmentInteractionListener mListener;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactUsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactUsFragment newInstance(String param1, String param2) {
        ContactUsFragment fragment = new ContactUsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Intent newInstagramProfileIntent(PackageManager pm, String url) {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            if (pm.getPackageInfo("com.instagram.android", 0) != null) {
                if (url.endsWith("/")) {
                    url = url.substring(0, url.length() - 1);
                }
                final String username = url.substring(url.lastIndexOf("/") + 1);
                // http://stackoverflow.com/questions/21505941/intent-to-open-instagram-user-profile-on-android
                intent.setData(Uri.parse("http://instagram.com/_u/" + username));
                intent.setPackage("com.instagram.android");
                return intent;
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        intent.setData(Uri.parse(url));
        return intent;
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
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);

        ButterKnife.bind(this, view);

        /*AdView adView = (AdView)view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);*/

        toolbar.setNavigationIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        snackView = getActivity().findViewById(R.id.home_layout);
        contactUsPresenter = new ContactUsPresenterImpl(this, new RetrofitContactUsProvider());
        contactUsPresenter.requestContactUs();

        imageLoader = new GlideImageLoader(getContext());


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
    public void showLoader(boolean show) {

        if (show) {

            contactUsLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
            contactUsLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMessage(String message) {
        Snackbar snackbar = Snackbar
                .make(snackView, message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    @Override
    public void setData(final ContactUsData contactUsData) {

        final String facebookUrl = "https://www.facebook.com/" + contactUsData.getFacebook();
     /*   final String twitterUrl="https://www.twitter.com/"+contactUsData.getTwitter();
        final String instagramUrl="https://www.instagram.com/"+contactUsData.getInstagram();
*/

        email.setText(contactUsData.getEmail());
        mobile.setText(contactUsData.getMobile());
        address.setText(contactUsData.getAddress());

/*
        websiteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bunchofools.com"));
                startActivity(browserIntent);
            }
        });*/


        facebook.setText(facebookUrl);
/*
        twitter.setText(twitterUrl);
        instagram.setText(instagramUrl);
*/
       // imageLoader.loadImage(contactUsData.getImage(), imageView, imageProgressBar);

/*

        facebookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    facebookIntent.setData(Uri.parse(getFacebookPageURL(getContext(), contactUsData.getFacebook(), contactUsData.getFacebook())));
                    startActivity(facebookIntent);
                }catch (Exception e){

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(facebookUrl));
                    startActivity(i);
                }

            }
        });

        twitterCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=null;
                try{
                    intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, "this is a tweet");
                    intent.setType("text/plain");
                    final PackageManager pm = getContext().getPackageManager();
                    final List<?> activityList = pm.queryIntentActivities(intent, 0);
                    int len =  activityList.size();
                    for (int i = 0; i < len; i++) {
                        final ResolveInfo app = (ResolveInfo) activityList.get(i);
                        if ("com.twitter.android.PostActivity".equals(app.activityInfo.name)) {
                            final ActivityInfo activity=app.activityInfo;
                            final ComponentName name=new ComponentName(activity.applicationInfo.packageName, activity.name);
                            intent.addCategory(Intent.CATEGORY_LAUNCHER);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                            intent.setComponent(name);
                            startActivity(intent);
                            break;
                        }
                    }
                }
                catch(final ActivityNotFoundException e) {
                    Log.i("twitter", "no twitter native",e );
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
                    startActivity(intent);

                }

                */
/*try {
                    // get the Twitter app if possible
                    getContext().getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id="+twitter));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    // no Twitter app, revert to browser
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
                }
                *//*
*/
/*getContext().startActivity(intent);*//*

            }
        }

        );

        instagramCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    startActivity(newInstagramProfileIntent(getContext().getPackageManager(),instagramUrl));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(instagramUrl)));
                }
            }
        });
*/

    }

    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context, String facebookUrl, String facebookPageId) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + facebookUrl;
            } else { //older versions of fb app
                return "fb://page/" + facebookPageId;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return facebookUrl; //normal web url
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        contactUsPresenter.onDestroy();
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
