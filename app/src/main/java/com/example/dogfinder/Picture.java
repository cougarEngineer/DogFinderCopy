package com.example.dogfinder;

import android.graphics.Bitmap;

import java.net.URL;

public class Picture {
    private Bitmap bitmap;
    private URL url;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
