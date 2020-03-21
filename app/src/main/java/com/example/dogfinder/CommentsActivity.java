package com.example.dogfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class CommentsActivity extends AppCompatActivity {

    private static final String USERNAME = "username";
    private DogProfile profile;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Gson gson = new Gson();
    private String dpid;
    private ListView commentsList;
    private EditText editComments;
    private Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        commentsList = findViewById(R.id.commentsList);
        editComments = findViewById(R.id.editComments);
        post = findViewById(R.id.postButton);
        dpid = getIntent().getStringExtra("id");
        db.collection("profiles").document(dpid).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("get comments profile", "DocumentSnapshot data: " + document.getData());
                            profile = gson.fromJson((String) document.getData().get("profile"), DogProfile.class);
                            doWhenDataGot();
                        } else {
                            Log.d("get comments profile", "No such document");
                        }
                    } else {
                        Log.d("get comments profile", "Error getting document", task.getException());
                    }
                });
    }

    private void doWhenDataGot() {
        String[] comments = profile.getComments().toArray(new String[0]);
        commentsList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, comments));

    }

    public void onClickPost(View view) {
        String comment = editComments.getText().toString();
        SharedPreferences mPrefs = getDefaultSharedPreferences(getApplicationContext());
        String curUser = mPrefs.getString(USERNAME, "");
        if (comment.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Comment is empty!", Toast.LENGTH_SHORT).show();
        } else if (curUser == null || curUser == "") {
            Toast.makeText(getApplicationContext(), "You aren't signed in!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            List<String> comments = profile.getComments();
            comments.add(curUser + ": " + comment);
            profile.setComments(comments);
            Map<String, String> data = new HashMap<>();
            data.put("profile", gson.toJson(profile));
            db.collection("profiles").document(dpid)
                    .set(data)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("commentAdd", "DocumentSnapshot successfully written!");
                            startActivity(new Intent(CommentsActivity.this, MainActivity.class));
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("commentAdd", "Error writing document", e);
                            Toast.makeText(getApplicationContext(), "Error adding comment", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
        }
    }
}
