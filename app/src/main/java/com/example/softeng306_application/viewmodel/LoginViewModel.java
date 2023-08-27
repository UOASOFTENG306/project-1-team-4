package com.example.softeng306_application.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.softeng306_application.usecase.SignInUseCase;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginViewModel extends AndroidViewModel {
    private SignInUseCase signInUseCase;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        signInUseCase = signInUseCase.getInstance();
    }

    public Task<AuthResult> signIn(String email, String password){
        return signInUseCase.signInWithEmailAndPassword(email,password);
    }
}
