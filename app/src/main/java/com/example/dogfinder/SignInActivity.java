package com.example.dogfinder;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class SignInActivity extends AppCompatActivity {

    static final String USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
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
        TextView tv = findViewById(R.id.currentUserTextView);
        SharedPreferences mPrefs = getDefaultSharedPreferences(getApplicationContext());
        String curUser = mPrefs.getString(USERNAME, "");
        if (curUser.equals("")) {
            tv.setText("Not signed in");
        } else {
            tv.setText("Signed in as " + curUser);
        }
    }

    public void onClickSignInButton(View view) {
        EditText editText = findViewById(R.id.usernameField);
        String username = editText.getText().toString();
        TextView tv = findViewById(R.id.currentUserTextView);
        tv.setText("Signed in as " + username);
        SharedPreferences mPrefs = getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(USERNAME, username);
        prefsEditor.apply();
    }
}
