package com.example.softeng306_application.Repository;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public interface FirestoreCallback {
    void onCallback(List<DocumentSnapshot> documentList);
}
