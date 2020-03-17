package com.example.dogfinder;

import android.graphics.Bitmap;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Data-holding class representing a dog's picture
 */
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

    public void setUrl(String urlS) throws MalformedURLException {
        url = new URL(urlS);
    }
}
