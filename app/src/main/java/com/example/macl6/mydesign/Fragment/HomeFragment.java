package com.example.macl6.mydesign.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.macl6.mydesign.Adapter.CustomAdapter;
import com.example.macl6.mydesign.Model.DepthTransformer;
import com.example.macl6.mydesign.R;

import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final Object DepthTransformer = new DepthTransformer();
    private ViewPager viewPager, vpItem;
    private TabLayout tabLayout;
    private TextView tvMore;
    private LinearLayout llCatalogue, llVideo, llLocation, llProfile, llContact, llProject;
    CollapsingToolbarLayout collapsingToolbarLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onBind();
    }

    public void onBind() {
        // View Pager.......
        viewPager = (ViewPager) getActivity().findViewById(R.id.vpHome);
        vpItem = (ViewPager) getActivity().findViewById(R.id.vpRecentItem);
        tabLayout = (TabLayout) getActivity().findViewById(R.id.tlHomePage);
        CustomAdapter customAdapter = new CustomAdapter(getContext());
        viewPager.setAdapter(customAdapter);
        vpItem.setAdapter(customAdapter);
        vpItem.setPageTransformer(true, new DepthTransformer());
        tabLayout.setupWithViewPager(viewPager, true);

//        // Timer for Viewpager images.........
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(), 3000, 5000);

        tvMore = (TextView) getActivity().findViewById(R.id.tvSeeMorePdf);

        llVideo = (LinearLayout) getActivity().findViewById(R.id.llVideo);
        llCatalogue = (LinearLayout) getActivity().findViewById(R.id.llCatalogue);
        llContact = (LinearLayout) getActivity().findViewById(R.id.llContact);
        llLocation = (LinearLayout) getActivity().findViewById(R.id.llLocation);

        llVideo.setOnClickListener(this);
        llCatalogue.setOnClickListener(this);
        llContact.setOnClickListener(this);
        llLocation.setOnClickListener(this);
        tvMore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.llCatalogue: {
                replacePage(new CatalogueFragment());
                break;
            }
            case R.id.llContact: {
                replacePage(new ContactFragment());
                break;
            }
            case R.id.llVideo: {
                replacePage(new VideoFragment());
                break;
            }
            case R.id.llLocation: {
                replacePage(new LocationFragment());
                break;
            }
            case R.id.tvSeeMorePdf: {
                replacePage(new CatalogueFragment());
                break;
            }
        }
    }

    //    // View pager image Slider time..
    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            if (getActivity() != null) {
                (getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (viewPager.getCurrentItem() == 0) {
                            viewPager.setCurrentItem(1);
                        } else if (viewPager.getCurrentItem() == 1) {
                            viewPager.setCurrentItem(2);
                        } else if (viewPager.getCurrentItem() == 2) {
                            viewPager.setCurrentItem(3);
                        } else if (viewPager.getCurrentItem() == 3) {
                            viewPager.setCurrentItem(4);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }
    }

    // Fragment Replace Page
    public void replacePage(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame, fragment, null);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
