package com.example.macl6.mydesign.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.macl6.mydesign.R;

public class SingleItemPager extends PagerAdapter {

    private int mProductImg[] = {R.drawable.pic_1, R.drawable.pic_2, R.drawable.pic_3, R.drawable.pic_4, R.drawable.pic_5,
            R.drawable.pic_6, R.drawable.pic_7, R.drawable.pic_8, R.drawable.pic_9, R.drawable.pic_1, R.drawable.pic_2, R.drawable.pic_3};

    private Context ctx;
    private LayoutInflater layoutInflater;

    public SingleItemPager(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return mProductImg.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (CardView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.single_product_item, container, false);
        ImageView imageView = view.findViewById(R.id.ivPdSingle);
        imageView.setImageResource(mProductImg[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((CardView) object);
    }
}
