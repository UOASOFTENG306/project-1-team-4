package com.example.softeng306_application.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.softeng306_application.Entity.User;
import com.example.softeng306_application.Repository.UserRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepository = userRepository.getInstance();
    }

    public Task<AuthResult> signIn(String email, String password){
        return userRepository.signIn(email, password);
    }
}
