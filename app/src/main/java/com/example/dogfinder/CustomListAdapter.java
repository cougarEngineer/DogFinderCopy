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
import java.time.LocalDate;
import java.util.List;
import com.bumptech.glide.Glide;

/**
 * CustomListAdapter houses the custom adapter that allows the listView to display images
 * and text on the Dog Profiles List screen.
 */
public class CustomListAdapter extends ArrayAdapter<DogProfile> {

    private int resourceLayout;
    private Context mContext;
    List<DogProfile> dogP;
    private String url = "https://i.imgur.com/MU2dD8E.jpg";

    /**
     * CustomListAdapter constructor takes a context, resource, and a DogProfile list.
     * @param context The current context.
     * @param resource The resource file that houses the template for the listView items.
     * @param profile The DogProfile list that contains the profile values from the database.
     */
    public CustomListAdapter(Context context, int resource, List<DogProfile> profile) {
        super(context, resource, profile);
        this.resourceLayout = resource;
        this.mContext = context;
        Log.d(this.toString(), "starting custom adapter");
    }

    /**
     * @param position The current position of the list item.
     * @param convertView The view that is adjusted.
     * @param parent The parent View.
     * @return The adjusted View is returned.
     */
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

        Log.d(this.toString(), "Setting the picture for the imageView");
        if (profile.getPicture() == null) {
            Glide.with(mContext).load(url).into(imageView);
        } else {
            Glide.with(mContext).load(profile.getPicture().getUrl()).into(imageView);
        }

        return customView;
    }


}
