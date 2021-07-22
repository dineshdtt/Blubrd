package com.example.macl6.mydesign.Activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.macl6.mydesign.Adapter.SingleItemPager;
import com.example.macl6.mydesign.Model.DepthTransformer;
import com.example.macl6.mydesign.R;

public class SingleItemActivity extends AppCompatActivity {

    private ViewPager vpItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);

        onBind();
    }

    public void onBind() {
        vpItem = (ViewPager) findViewById(R.id.vpSingleItem);
        SingleItemPager singleItemPager = new SingleItemPager(this);
        vpItem.setAdapter(singleItemPager);
        vpItem.setPageTransformer(true,new DepthTransformer());

    }
}
