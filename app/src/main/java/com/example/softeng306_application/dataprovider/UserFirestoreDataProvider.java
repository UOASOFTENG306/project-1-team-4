package com.example.softeng306_application.dataprovider;

import com.example.softeng306_application.Entity.European;
import com.example.softeng306_application.Entity.FastFood;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Repository.UserRepository;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class UserFirestoreDataProvider {

    private UserRepository userRepository;

    public UserFirestoreDataProvider() {
        userRepository = userRepository.getInstance();
    }

}
