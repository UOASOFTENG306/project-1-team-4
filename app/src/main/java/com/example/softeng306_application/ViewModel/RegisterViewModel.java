package com.example.softeng306_application.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.softeng306_application.Entity.User;
import com.example.softeng306_application.Repository.UserRepository;

public class RegisterViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private User user;
    public RegisterViewModel(@NonNull Application application) {
        super(application);
        userRepository = userRepository.getInstance();

    }

    public void register(String email, String password){
        userRepository.register(email, password);
    }
}
