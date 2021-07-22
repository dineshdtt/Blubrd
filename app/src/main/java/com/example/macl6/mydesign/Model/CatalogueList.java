package com.example.macl6.mydesign.Model;

import android.graphics.Bitmap;

public class CatalogueList {

    private String title;
    private Bitmap itemPic;

    public CatalogueList(String title, Bitmap itemPic) {
        this.title = title;
        this.itemPic = itemPic;
    }

    public Bitmap getItemPic() {
        return itemPic;
    }

    public void setItemPic(Bitmap itemPic) {
        this.itemPic = itemPic;
    }
}
