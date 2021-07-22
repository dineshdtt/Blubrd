package com.example.macl6.mydesign.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.macl6.mydesign.Activity.YoutubeActivity;
import com.example.macl6.mydesign.R;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private int[] Images;
    private String[] pName;

    public ItemAdapter(Context context, int[] Images,String[] pName) {
        this.context = context;
        this.Images = Images;
        this.pName = pName;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.album_card, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder viewHolder, int i) {
        int imgId = Images[i];
        viewHolder.itemPic.setImageResource(imgId);
        String title = pName[i];
        viewHolder.itemName.setText(title);
    }

    @Override
    public int getItemCount() {
        return Images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         TextView itemName;
         ImageView itemPic, itemDownload;
         RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                itemName = itemView.findViewById(R.id.title);
                itemPic = itemView.findViewById(R.id.thumbnail);
                itemDownload = itemView.findViewById(R.id.icon);
                relativeLayout = itemView.findViewById(R.id.rlCard);

                relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context,YoutubeActivity.class);
                        context.startActivity(intent);
                    }
                });

            } catch (Exception | Error e) {
                e.printStackTrace();
            }
        }
    }
}
