package com.example.macl6.mydesign.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.macl6.mydesign.Adapter.CustomAdapter;
import com.example.macl6.mydesign.Adapter.SingleItemPager;
import com.example.macl6.mydesign.Model.DepthTransformer;
import com.example.macl6.mydesign.R;

import java.util.Timer;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingleProductFragment extends Fragment {

    private ViewPager vpItem;


    public SingleProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_product, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onBind();
    }

    public void onBind() {
        vpItem = (ViewPager)getActivity().findViewById(R.id.vpSingleItem);
        SingleItemPager singleItemPager = new SingleItemPager(getContext());
        vpItem.setAdapter(singleItemPager);
        vpItem.setPageTransformer(true,new DepthTransformer());

    }
}
