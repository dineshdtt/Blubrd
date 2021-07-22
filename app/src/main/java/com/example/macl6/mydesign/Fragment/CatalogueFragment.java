package com.example.macl6.mydesign.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macl6.mydesign.Adapter.CatalogueRecycleAdapter;
import com.example.macl6.mydesign.Adapter.ItemAdapter;
import com.example.macl6.mydesign.R;

public class CatalogueFragment extends Fragment {

    private RecyclerView.Adapter adapter;
    private int mProductImg[] = {R.drawable.pic_1, R.drawable.pic_2, R.drawable.pic_3, R.drawable.pic_4, R.drawable.pic_5,
            R.drawable.pic_6, R.drawable.pic_7, R.drawable.pic_8, R.drawable.pic_9, R.drawable.pic_1, R.drawable.pic_2, R.drawable.pic_3};
    private String[] pName = {"KADI", "KHUTI", "KNOB", "MAGNET", "BUFFER", "DOOR STOPPER", "DOOR EYE", "CABINET HANDLE", "AUTO HINGES", "DOOR CLOSER", "DRAWER CHANNEL", "SCREW", "BED FITTINGS"};

    public CatalogueFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catalogue, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(new CatalogueRecycleAdapter(getContext(),mProductImg,pName));

    }
}
