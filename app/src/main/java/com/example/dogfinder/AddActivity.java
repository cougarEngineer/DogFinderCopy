package com.example.dogfinder;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddActivity extends AppCompatActivity {

    EditText name, picture, height, weight, info, city, state, address, contact;
    Spinner breed, color;
    Button create, cancel;

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
                String addressS = address.getText().toString();
                String contactS = contact.getText().toString();
                String breedS = breed.getSelectedItem().toString();
                String colorS = color.getSelectedItem().toString();

                Intent intent = new Intent(AddActivity.this, ShowProfileActivity.class);
                intent.putExtra("name", nameS);
                intent.putExtra("height",heightS);
                intent.putExtra("weight",weightS);
                intent.putExtra("info",infoS);
                intent.putExtra("city",cityS);
                intent.putExtra("address",addressS);
                intent.putExtra("contact",contactS);
                intent.putExtra("breed",breedS);
                intent.putExtra("color",colorS);
                startActivity(intent);
                //****************************************
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
