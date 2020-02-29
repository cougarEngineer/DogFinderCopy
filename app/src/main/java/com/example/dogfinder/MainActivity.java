package com.example.dogfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showList(View view) {
        Intent i = new Intent(this, ListActivity.class);
        startActivity(i);
    }

    public void showAdd(View view) {
        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }

    public void showSignIn(View view) {
        Intent i = new Intent(this, SignInActivity.class);
        startActivity(i);
    }
}

