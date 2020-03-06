package com.example.dogfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ShowProfileActivity extends AppCompatActivity {

    TextView name, breed, color, height, weight, info, city, state, address, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);

        name = findViewById(R.id.getName);
        breed = findViewById(R.id.getBreed);
        color = findViewById(R.id.getColor);
        height = findViewById(R.id.getHeight);
        weight = findViewById(R.id.getWeight);
        info = findViewById(R.id.getInfo);
        city = findViewById(R.id.getCity);
        state = findViewById(R.id.getState);
        address = findViewById(R.id.getAddress);
        contact = findViewById(R.id.getContact);

        //*******Display Profile Check******
        Intent intent = getIntent();
        String nameS = intent.getStringExtra("name");
        String heightS = intent.getStringExtra("height");
        String weightS = intent.getStringExtra("weight");
        String infoS = intent.getStringExtra("info");
        String cityS = intent.getStringExtra("city");
        String addressS = intent.getStringExtra("address");
        String contactS = intent.getStringExtra("contact");
        String breedS = intent.getStringExtra("breed");
        String colorS = intent.getStringExtra("color");
        name.setText(nameS);
        height.setText(heightS);
        weight.setText(weightS);
        info.setText(infoS);
        city.setText(cityS);
        address.setText(addressS);
        contact.setText(contactS);
        breed.setText(breedS);
        color.setText(colorS);
        //*********************************


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
