package com.example.softeng306_application.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.softeng306_application.Repository.UserRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public class MainViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        userRepository = userRepository.getInstance();
    }
    public Task<DocumentSnapshot> getUserInfo() {
        return userRepository.getAllUserInformation();
    }

    public void logout(){
        userRepository.logout();
    }
}
