package com.example.dogfinder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter<DogProfile> {

    private int resourceLayout;
    private Context mContext;
    List<DogProfile> dogP;
    private String url = "https://i.imgur.com/MU2dD8E.jpg";

    public CustomListAdapter(Context context, int resource, List<DogProfile> profile) {
        super(context, resource, profile);
        this.resourceLayout = resource;
        this.mContext = context;
        Log.d(this.toString(), "starting custom adapter");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(this.toString(), "starting getView");
        LayoutInflater inflater = LayoutInflater.from(mContext);
        Log.d(this.toString(), "initializing inflater");
        View customView = inflater.inflate(R.layout.custom_list_template, parent, false);

        DogProfile profile = getItem(position);
        Log.d(this.toString(), "setting textView");
        TextView textView1 = customView.findViewById(R.id.listName);
        TextView textView2 = customView.findViewById(R.id.listLocation);
        TextView textView3 = customView.findViewById(R.id.listCity);
        TextView textView4 = customView.findViewById(R.id.listState);
        ImageView imageView = customView.findViewById(R.id.listImage);

        Log.d(this.toString(), "getting Name and Location for textView");
        textView1.setText(String.valueOf(profile.getName()));

        if (profile.getLocation() != null && profile.getLocation().getLocal() != null) {
            textView2.setText(String.valueOf(profile.getLocation().getLocal()));
        }
        if (profile.getLocation() != null && profile.getLocation().getCity() != null) {
            textView3.setText(String.valueOf(profile.getLocation().getCity()));
        }
        if (profile.getLocation() != null && profile.getLocation().getState() != null) {
            textView4.setText(String.valueOf(profile.getLocation().getState()));
        }

        /*
        // Image on List
        InputStream is = null;
        try {
            is = (InputStream) new URL(url).getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d = Drawable.createFromStream(is, "url");
        imageView.setImageDrawable(d);
         */


        return customView;
    }


}
