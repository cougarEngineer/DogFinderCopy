package com.example.dogfinder;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


/**
 * For right now, only contains code to add a DogProfile to the app's database
 */
public class DogProfileDatabaseInteractor {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Gson gson = new Gson();

    /**
     * Adds a DogProfile to the database
     *
     * @param dp The DogProfile to be added to the database
     */
    public void add(DogProfile dp) {
        Map<String, String> data = new HashMap<>();
        data.put("profile", gson.toJson(dp));
        db.collection("profiles")
                .add(data)
                .addOnSuccessListener(documentReference -> Log.d("database set", "DocumentSnapshot written with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w("database set", "Error adding document", e));
    }

    public void delete(DogProfile dp) {
        String json = gson.toJson(dp);
        Log.d("deleteDogProfile", "starting deletion with " + json);
        db.collection("profiles").whereEqualTo("profile", json).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("deleteDogProfile", "found item " + document.getData());
                            db.collection("profiles").document(document.getId())
                                    .delete()
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d("deleteDogProfile", "DocumentSnapshot successfully deleted!");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("deleteDogProfile", "Error deleting document", e);
                                        }
                                    });
                        }
                    } else {
                        Log.d("deleteDogProfile", "Error finding dogProfile ", task.getException());
                    }
                });
    }
}
