package com.example.dogfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.net.MalformedURLException;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class AddActivity extends AppCompatActivity {

    EditText name, url, height, weight, info, city, address, contact;
    Spinner breed, color, sex, state;
    Button create, cancel;
    static final String USERNAME = "username";
    static final String[] STATELIST = {"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //Hooks
        name = findViewById(R.id.nameET);
        url = findViewById(R.id.urlET);
        breed = findViewById(R.id.breedSpinner);
        color = findViewById(R.id.colorSpinner);
        sex = findViewById(R.id.sexSpinner);
        height = findViewById(R.id.heightET);
        weight = findViewById(R.id.weightET);
        info = findViewById(R.id.infoET);
        city = findViewById(R.id.cityET);
        state = findViewById(R.id.stateSpinner);
        address = findViewById(R.id.addressET);
        contact = findViewById(R.id.contactET);
        create = findViewById(R.id.createBtn);

        breed.setAdapter(new ArrayAdapter<Breed>(this, android.R.layout.simple_spinner_item, Breed.values()));
        color.setAdapter(new ArrayAdapter<Color>(this, android.R.layout.simple_spinner_item, Color.values()));
        sex.setAdapter(new ArrayAdapter<Sex>(this, android.R.layout.simple_spinner_item, Sex.values()));
        state.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, STATELIST));
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean hasImage = true;
                //****************Get User Input**********
                String nameS = name.getText().toString();
                String urlS = url.getText().toString();
                String heightS = height.getText().toString();
                String weightS = weight.getText().toString();
                String infoS = info.getText().toString();
                String cityS = city.getText().toString();
                String stateS = state.getSelectedItem().toString();
                String addressS = address.getText().toString();
                String contactS = contact.getText().toString();

//                Intent intent = new Intent(AddActivity.this, ShowProfileActivity.class);
//                intent.putExtra("name", nameS);
//                intent.putExtra("height",heightS);
//                intent.putExtra("weight",weightS);
//                intent.putExtra("info",infoS);
//                intent.putExtra("city",cityS);
//                intent.putExtra("address",addressS);
//                intent.putExtra("contact",contactS);
//                intent.putExtra("breed",breedS);
//                intent.putExtra("color",colorS);
//                startActivity(intent);

                DogProfile profile = new DogProfile();
                SharedPreferences mPrefs = getDefaultSharedPreferences(getApplicationContext());
                String curUser = mPrefs.getString(USERNAME, "");
                User user = new User();
                user.setToken(curUser);
                profile.setUser(user);
                Picture picture = new Picture();
                try {
                    picture.setUrl(urlS);
                } catch (MalformedURLException e) {
                    hasImage = false;
                    Log.e("AddActivity", "MalformedURLException");
                    e.printStackTrace();
                }
                profile.setPicture(picture);
                profile.setName(nameS);
                profile.setHeight(heightS);
                profile.setWeight(weightS);
                profile.setOther(infoS);
                Location location = new Location();
                location.setCity(cityS);
                location.setState(stateS);
                location.setLocal(addressS);
                profile.setLocation(location);
                ContactInfo contactInfo = new ContactInfo();
                contactInfo.setUser(user);
                contactInfo.setPhone(contactS);
                profile.setContact(contactInfo);
                profile.setBreed((Breed) breed.getSelectedItem());
                profile.setColor((Color) color.getSelectedItem());
                profile.setSex((Sex) sex.getSelectedItem());
                new DogProfileDatabaseInteractor().add(profile);
                if (hasImage) {
                    Toast.makeText(getApplicationContext(), "Dog added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Dog added without image", Toast.LENGTH_SHORT).show();
                }


                //****************************************
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void goBack(View view) {
        startActivity(new Intent(AddActivity.this, MainActivity.class));
    }

}
