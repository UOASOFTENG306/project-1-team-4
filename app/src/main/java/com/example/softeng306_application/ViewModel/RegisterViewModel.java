package com.example.softeng306_application.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.softeng306_application.Entity.User;
import com.example.softeng306_application.Repository.UserRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class RegisterViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private User user;
    public RegisterViewModel(@NonNull Application application) {
        super(application);
        userRepository = userRepository.getInstance();
    }

    public Task<AuthResult> register(String email, String password, String username){
        return userRepository.register(email, password, username);
    }

    public void addToDb(String email, String password, String username) {
        userRepository.addUserToDb(email, password, username);
    }
}
