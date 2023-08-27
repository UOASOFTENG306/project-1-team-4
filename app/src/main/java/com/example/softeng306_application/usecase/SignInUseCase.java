package com.example.softeng306_application.usecase;
import com.example.softeng306_application.repository.UserRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignInUseCase {

    private static SignInUseCase instance;
    private UserRepository userRepository;

    private SignInUseCase(){
        userRepository = userRepository.getInstance();
    }

    public static SignInUseCase getInstance() {
        if (instance == null){
            instance = new SignInUseCase();
        }
        return instance;
    }

    public Task<AuthResult> signInWithEmailAndPassword(String email, String password){
        return userRepository.signIn(email, password);
    }
}
