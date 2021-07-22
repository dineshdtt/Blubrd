package com.example.macl6.mydesign.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macl6.mydesign.Activity.SingleItemActivity;
import com.example.macl6.mydesign.Fragment.HomeFragment;
import com.example.macl6.mydesign.Fragment.SingleProductFragment;
import com.example.macl6.mydesign.R;
import com.example.macl6.mydesign.Utils.CommonFunctions;


public class CatalogueRecycleAdapter extends RecyclerView.Adapter<CatalogueRecycleAdapter.MyViewHolder> {

    private Context ctx;
    private int[] mProductImg;
    private String[] pName;


    public CatalogueRecycleAdapter(Context ctx, int[] mProductImg, String[] pName) {
        this.ctx = ctx;
        this.mProductImg = mProductImg;
        this.pName = pName;
    }

    @NonNull
    @Override
    public CatalogueRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.catalogue_item_rv, viewGroup, false);
        return new CatalogueRecycleAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogueRecycleAdapter.MyViewHolder myViewHolder, int i) {

        int imgId = mProductImg[i];
        myViewHolder.ivProduct.setImageResource(imgId);
        String title = pName[i];
        myViewHolder.tvTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return mProductImg.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivProduct;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                tvTitle = (TextView) itemView.findViewById(R.id.tvPdTitle);
                ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct);

                ivProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctx.startActivity(new Intent(ctx, SingleItemActivity.class));
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
