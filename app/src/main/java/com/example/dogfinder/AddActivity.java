package com.example.dogfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class AddActivity extends AppCompatActivity {

    EditText name, picture, height, weight, info, city, state, address, contact;
    Spinner breed, color;
    Button create, cancel;
    static final String USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //Hooks
        name = findViewById(R.id.nameET);
        picture = findViewById(R.id.urlET);
        breed = findViewById(R.id.breedSpinner);
        color = findViewById(R.id.colorSpinner);
        height = findViewById(R.id.heightET);
        weight = findViewById(R.id.weightET);
        info = findViewById(R.id.infoET);
        city = findViewById(R.id.cityET);
        state = findViewById(R.id.stateET);
        address = findViewById(R.id.addressET);
        contact = findViewById(R.id.contactET);
        create = findViewById(R.id.createBtn);
        breed.setAdapter(new ArrayAdapter<Breed>(this, android.R.layout.simple_spinner_item, Breed.values()));
        color.setAdapter(new ArrayAdapter<Color>(this, android.R.layout.simple_spinner_item, Color.values()));

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //****************Get User Input**********
                String nameS = name.getText().toString();
                String heightS = height.getText().toString();
                String weightS = weight.getText().toString();
                String infoS = info.getText().toString();
                String cityS = city.getText().toString();
                String stateS = state.getText().toString();
                String addressS = address.getText().toString();
                String contactS = contact.getText().toString();
                String breedS = breed.getSelectedItem().toString();
                String colorS = color.getSelectedItem().toString();

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
                profile.setName(nameS);
                profile.setHeight(Integer.parseInt(heightS));
                profile.setWeight(Integer.parseInt(weightS));
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
                new DogProfileDatabaseInteractor().add(profile);

                Toast.makeText(getApplicationContext(), "Dog added" ,Toast.LENGTH_SHORT).show();

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
