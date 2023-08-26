package com.example.softeng306_application.UseCase;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softeng306_application.Entity.Review;
import com.example.softeng306_application.Repository.UserRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public class GetCurrentUserUseCase {

    private static GetCurrentUserUseCase instance;
    private UserRepository userRepository;

    private GetCurrentUserUseCase(){
        userRepository = userRepository.getInstance();
    }

    public static GetCurrentUserUseCase getInstance() {
        if (instance == null){
            instance = new GetCurrentUserUseCase();
        }
        return instance;
    }

    public LiveData<String> getUserInfo() {
        MutableLiveData<String> name = new MutableLiveData<>();
        userRepository.getAllUserInformation().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                try {
                    DocumentSnapshot document = task.getResult();
                    String username = (String) document.get("username");
                    if(username != null) {
                        name.setValue(username);
                    }
                } catch (Exception e) {
                    Log.d("FirestoreActivity", "Error getting the username: ", task.getException());
                }
            }
            else {
                Log.d("FirestoreActivity", "Error getting documents: ", task.getException());
            }

        });
        return name;
    }

    public void logout(){
        userRepository.logout();
    }
}
