package com.example.dogfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * The type Sign in activity.
 */
public class SignInActivity extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mLoginBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    /**
     * The Username.
     */
    static final String USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEmail = findViewById(R.id.emailField);
        mPassword = findViewById(R.id.passwordField);
        mLoginBtn = findViewById(R.id.signInButton);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
    }

    /**
     * On click sign in button.
     *
     * @param view the view
     */
    public void onClickSignInButton(View view) {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            mEmail.setError("Please Enter Email");
            return;
        }

        if(TextUtils.isEmpty(password)) {
            mPassword.setError("Please Enter Password");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        // User Authentication

        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(SignInActivity.this,"Sign in Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(SignInActivity.this, "Error: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void onClickRegisterTextView(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }
}
