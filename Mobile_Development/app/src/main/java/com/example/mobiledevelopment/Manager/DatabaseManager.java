package com.example.mobiledevelopment.Manager;


import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private static DatabaseManager instance; // Singleton instance

    private DatabaseManager() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    public Task<Void> updateUser(String uid, Map<String, Object> updates) {
        return db.collection("users").document(uid).update(updates);
    }

    public Task<QuerySnapshot> getProperties(String propertyType) {
        return db.collectionGroup("properties")
                .whereEqualTo("propertyType", propertyType)
                .get();
    }
}
