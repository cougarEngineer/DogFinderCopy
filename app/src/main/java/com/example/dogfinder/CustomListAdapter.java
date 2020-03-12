package com.example.dogfinder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<DogProfile> {

    private int resourceLayout;
    private Context mContext;
    List<DogProfile> dogP;

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
        TextView textView1 = (TextView) customView.findViewById(R.id.listName);
        TextView textView2 = (TextView) customView.findViewById(R.id.listLocation);

        Log.d(this.toString(), "getting Name and Location for textView");
        textView1.setText(String.valueOf(profile.getName()));
        textView2.setText(String.valueOf(profile.getLocation().getLocal()));
        return customView;


/*
//Test Code - Do Not Uncomment

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(resourceLayout, null);
            Log.d(this.toString(), "starting inflater");
        }

        DogProfile dp = getItem(position);
        Log.d(this.toString(), "setting adapter item");
        if (dp != null) {
            TextView textView1 = (TextView) view.findViewById(R.id.listName);

            if (textView1 != null) {
                textView1.setText(dp.getName());
            }
        }
        Log.d(this.toString(), "returning view");
        return view;
 */
    }


}
