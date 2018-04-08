package com.jojoflower.restapp;

import android.graphics.Bitmap;

/**
 * Created by lou-l on 27/03/2018.
 */

public class MyImage {

    String name;

    public String getName() {
        return name;
    }

    String url;

    public String getUrl() {
        return url;
    }

    public MyImage(String name, String url) {
        this.name = name;
        this.url = url;
    }

    Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap b) {
        bitmap = b;
    }
}
