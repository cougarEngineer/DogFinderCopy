package com.example.dogfinder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;

import java.io.InputStream;
import java.net.URL;

public class ShowProfileActivity extends AppCompatActivity {
    ImageView image;
    TextView name, breed, color, height, weight, otherInfo, city, state, address, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);

        Gson gson = new Gson();

        //dogProfile object in JSON form
        DogProfile dogProfile = new DogProfile();
        String json = gson.toJson(dogProfile);

        //GSON deserialization
        DogProfile profile = gson.fromJson(json, DogProfile.class);

        //xml view id's
        name = findViewById(R.id.getName);
        breed = findViewById(R.id.getBreed);
        color = findViewById(R.id.getColor);
        height = findViewById(R.id.getHeight);
        weight = findViewById(R.id.getWeight);
        otherInfo = findViewById(R.id.getInfo);
        contact = findViewById(R.id.getContact);
        city = findViewById(R.id.getCity);
        state = findViewById(R.id.getState);
        address = findViewById(R.id.getAddress);
        image = findViewById(R.id.getPic);

        // TextView displayJsonObject
        name.setText(String.valueOf(profile.getName()));
        breed.setText(String.valueOf(profile.getBreed()));
        color.setText(String.valueOf(profile.getColor()));
        height.setText(String.valueOf(profile.getHeight()));
        weight.setText(String.valueOf(profile.getWeight()));
        otherInfo.setText(String.valueOf(profile.getOther()));
        contact.setText(String.valueOf(profile.getContact()));


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

/*-------------------------Get image---------------------
    public class LoadImage extends AsyncTask<Void,Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(Void... params) {
            Bitmap bitmap=null;

            try {
                bitmap= BitmapFactory.decodeStream((InputStream)new URL(" ").getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            Log.v("ShowProfileActivity","Getting image");
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap!=null) {
                image.setImageBitmap(bitmap);
            } else {
                Log.v("ShowProfileActivity","Some error occurred! Unable to get image");
            }
        }
    }
------------------------------------------------------*/
}
