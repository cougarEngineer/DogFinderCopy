package com.example.dogfinder;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

/**
 * The type Show profile activity.
 */
public class ShowProfileActivity extends AppCompatActivity {
    /**
     * The image from Url.
     */
    String URL; // = "https://i.imgur.com/sdEz1o7.jpg";
    /**
     * The Image.
     */
    ImageView image;
    /**
     * The Name.
     */
    /**
     *
     */
    TextView name, /**
     * The Breed of dog.
     */
    breed, /**
     * The Color of the dog.
     */
    color, /**
     * The Height of the dog.
     */
    height, /**
     * The Weight of the dog.
     */
    weight, /**
     * The Other info.
     */
    otherInfo, /**
     * The City.
     */
    city, /**
     * The State.
     */
    state, /**
     * The Address.
     */
    address, /**
     * The Contact information.
     */
    contact,
            sex,
            user;

    /**
     * onCreate gets json object and deserialize the object.
     * It then displays contents info into Textview.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);

        Gson gson = new Gson();

        //dogProfile object in JSON form


        String json = getIntent().getStringExtra("profile");

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
        user = findViewById(R.id.getUser);
        sex = findViewById(R.id.getSex);
        // TextView displayJsonObject
        if (profile.getPicture() != null && profile.getPicture().getUrl() != null) {
            URL = profile.getPicture().getUrl().toString();
        } else {
            URL = "https://i.imgur.com/SdumAU9.gif";
        }
        name.setText("Name: " + profile.getName());
        breed.setText("Breed: " + profile.getBreed());
        color.setText("Color: " + profile.getColor());
        height.setText("Height: " + profile.getHeight());
        weight.setText("Weight: " + profile.getWeight());
        otherInfo.setText("Other Info: " + profile.getOther());
        contact.setText("Contact Info: " + profile.getContact().getPhone());
        address.setText("Local: " + profile.getLocation().getLocal());
        city.setText("City: " + profile.getLocation().getCity());
        state.setText("State: " + profile.getLocation().getState());
        user.setText("Username: " + profile.getUser().getToken());
        Sex s = profile.getSex();
        if (s == null) {
            s = Sex.Unknown;
        }
        sex.setText("Sex: " + s);
        //Load image from URL
        image = findViewById(R.id.getPic);
        Glide.with(this).load(URL).into(image);

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

    public void onClickDelete(View view) {
        Toast.makeText(getApplicationContext(), "You are not the owner of this dog", Toast.LENGTH_SHORT).show();
    }

    public void onClickComments(View view) {
        Toast.makeText(getApplicationContext(), "Comments button", Toast.LENGTH_SHORT).show();
    }
}
