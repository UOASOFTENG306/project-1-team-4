package com.example.softeng306_application.UseCase;

import com.example.softeng306_application.Repository.UserRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class RegisterUserUseCase {

    private static RegisterUserUseCase instance;
    private UserRepository userRepository;

    private RegisterUserUseCase(){
        userRepository = userRepository.getInstance();
    }

    public static RegisterUserUseCase getInstance() {
        if (instance == null){
            instance = new RegisterUserUseCase();
        }
        return instance;
    }

    public Task<AuthResult> register(String email, String password, String username){
        return userRepository.register(email, password, username);
    }

    public void addUserToDb(String email, String password, String username) {
        userRepository.addUserToDb(email, password, username);
    }
}
