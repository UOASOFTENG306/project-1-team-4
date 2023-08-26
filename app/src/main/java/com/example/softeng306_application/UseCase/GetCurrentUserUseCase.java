package com.example.softeng306_application.UseCase;

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

    public Task<DocumentSnapshot> getUserInfo() {
        return userRepository.getAllUserInformation();
    }

    public void logout(){
        userRepository.logout();
    }
}
